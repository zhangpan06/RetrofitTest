package com.example.zhangpan.myretrofitdemo.Service;

import android.util.Log;

import com.example.zhangpan.myretrofitdemo.Interface.RetrofitInterface;
import com.example.zhangpan.myretrofitdemo.constants.RequestConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangpan on 16/8/22.
 */
public class RetrofitService {
    private static RetrofitService retrofitService = new RetrofitService();
    private RetrofitInterface retrofitInterface;

    private RetrofitService() {
        initRetrofit();
    }

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        return retrofitService;
    }

    private void initRetrofit() {
        OkHttpClient.Builder httpClient;
        Retrofit mRetrofit;

        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new RequestIntercepter());
        mRetrofit = new Retrofit.Builder()
                .baseUrl(RequestConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        retrofitInterface = mRetrofit.create(RetrofitInterface.class);
    }

    public RetrofitInterface getRetrofitInterface() {
        return retrofitInterface;
    }

    private class RequestIntercepter implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
//            requestBuilder.addHeader("Cache-Control", "max-age=640000");
            Request request = requestBuilder.build();

            Log.i("RetrofitService","发送请求的URL为："+original.url());
            return chain.proceed(request);
        }
    }
}

