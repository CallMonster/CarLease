package com.tj.pxdl.carlease.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.builder.HeadBuilder;
import com.tj.chaersi.okhttputils.cache.CacheInterceptor;
import com.tj.chaersi.okhttputils.https.HttpsUtils;
import com.tj.chaersi.okhttputils.interceptor.HeaderInterceptor;
import com.tj.chaersi.okhttputils.log.LoggerInterceptor;
import com.tj.pxdl.carlease.utils.AppUtils;
import com.tj.pxdl.carlease.widget.ActivityManagerCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Chaersi on 17/2/27.
 */
public class BaseApplication extends Application {

    public static BaseApplication instance;
    public static Gson gson;
//    private String cerPath= "certificate/server.cer";
//    private String bksPath= "certificate/sign.bks";
    private String cerPath = "certificate/server.cer";
    private String bksPath = "certificate/client.p12";

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        gson=new Gson();
        instance=this;

//        Client-Type： ios/android //ios或者android为小写字符串；
//        Client-Version：客户端版本号//建议为1.00这样的数字样式字符串

        HashMap<String,String> headerMap=new HashMap<>();
        headerMap.put("Client-Type","android");
        headerMap.put("Client-Version",AppUtils.getAppVersionName(getApplicationContext()));

//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getApplicationContext(),cerPath, bksPath, "1234abcD");
//        .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggerInterceptor("carleasePro"))
                .cookieJar(cookies)
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).build();

        OkHttpUtils.initClient(okHttpClient);

        registerActivityLifecycleCallbacks(new ActivityManagerCallBack());
    }

    CookieJar cookies=new CookieJar() {
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieStore.put(url, cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    };

    /**
     * 清空所有act
     */
    public void clearAct() {
        for (Activity activity : ActivityManagerCallBack.mActivityLinkedList) {
            activity.finish();
        }
        if (ActivityManagerCallBack.mActivityLinkedList.size() == 0)
            ActivityManagerCallBack.mActivityLinkedList.clear();
    }
}
