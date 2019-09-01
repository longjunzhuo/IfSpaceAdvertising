package com.ifspace.advertising.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sheng on 19/9/1.
 */
public class HttpRequestFactor {

    private static HttpRequestFactor sInstance;

    public static HttpRequestFactor getInstance() {
        if (sInstance == null) {
            synchronized (HttpRequestFactor.class) {
                if (sInstance == null) {
                    sInstance = new HttpRequestFactor();
                }
            }
        }
        return sInstance;
    }

    public <T> T createService(Class<T> service, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(HttpClient.getInstance().getOkHttpClient())
                .baseUrl(baseUrl)
                .build();

        return retrofit.create(service);
    }
}
