package com.example.zhangpan.myretrofitdemo.Service;

import android.util.Base64;
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
public class RetrofitAuthService {
    private static RetrofitAuthService retrofitAuthService = new RetrofitAuthService();
    private RetrofitInterface retrofitInterface;

    private RetrofitAuthService() {
        initRetrofit();
    }

    public static RetrofitAuthService getInstance() {
        if (retrofitAuthService == null) {
            retrofitAuthService = new RetrofitAuthService();
        }
        return retrofitAuthService;
    }

    private void initRetrofit() {
        OkHttpClient.Builder httpClient;
        Retrofit mRetrofit;

        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new RequestAuthIntercepter());
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

    private class RequestAuthIntercepter implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", RequestConstants.auth_string)
                    .header("Accept","application/json")
                    .method(original.method(),original.body());

            Request request = requestBuilder.build();
            Log.i("RetrofitService","发送请求的URL为："+original.url());
            return chain.proceed(request);
        }
    }
}


