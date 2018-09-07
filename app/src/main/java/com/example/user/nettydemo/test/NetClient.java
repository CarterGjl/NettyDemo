package com.example.user.nettydemo.test;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetClient {

    private static NetClient mNetClient = null;
    private final IMInterface mImInterfcae;

    private NetClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        mImInterfcae = new Retrofit.Builder()
                .baseUrl(NetUrl.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(IMInterface.class);
    }
    public static NetClient getInstance(){
        synchronized(NetClient.class){
            if(mNetClient == null){
                mNetClient = new NetClient();
            }
        }
        return mNetClient;
    }

    public static IMInterface create() {

        return NetClient.getInstance().mImInterfcae;
    }

}
