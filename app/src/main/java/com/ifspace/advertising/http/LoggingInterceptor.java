package com.ifspace.advertising.http;

import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by sheng on 19/9/1.
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        Log.d(HttpClient.TAG, String.format("发送请求 %s", request.url()));

        Response response = chain.proceed(request);

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        Log.d(HttpClient.TAG, String.format("接收响应: [%s] %n返回json:【%s】",
                response.request().url(),
                responseBody.string()));

        return response;
    }
}
