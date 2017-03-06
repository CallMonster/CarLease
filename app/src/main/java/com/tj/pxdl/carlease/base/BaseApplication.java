package com.tj.pxdl.carlease.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.cache.CacheInterceptor;
import com.tj.chaersi.okhttputils.https.HttpsUtils;
import com.tj.chaersi.okhttputils.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

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

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getApplicationContext(),cerPath, bksPath, "1234abcD");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggerInterceptor("carleasePro"))
                .addNetworkInterceptor(new CacheInterceptor())
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
}
