package com.ifspace.advertising.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by sheng on 19/9/1.
 */
public class HttpClient {

    public static final String TAG = "HttpClient";
    private static HttpClient sInstance = new HttpClient();
    private OkHttpClient mOkHttpClient;

    public static HttpClient getInstance() {
        return sInstance;
    }

    private HttpClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
