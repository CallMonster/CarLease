package com.tj.chaersi.okhttputils.interceptor;

import android.util.Log;

import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.builder.HeadBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Headers;

/**
 * Created by Chaersi on 17/3/15.
 * 添加统一header的拦截器
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request requestOrigin = chain.request();
//        Headers headersOrigin = requestOrigin.headers();
//        Headers headers = headersOrigin.newBuilder()
//                .set("Client-Type","android")
//                .set("Client-Version","1.0").build();
//        Request request = requestOrigin.newBuilder().headers(headers).build();
//        Response response = chain.proceed(request);

        Log.i("header","header");
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Client-Type","android")
                .addHeader("Client-Version","1.0").build();
        return chain.proceed(request);
    }

}
