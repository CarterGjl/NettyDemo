package com.example.user.nettydemo.test;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.user.nettydemo.test.IMInterface.KEY_URL_NAME;

public class HttpClient {

    private static HttpClient mNetClient = null;
    private final IMInterface mImInterfcae;

    private HttpClient(String url) {


        Interceptor urlInterceptor = chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            List<String> url_name = request.headers(KEY_URL_NAME);


            if (url_name != null && url_name.size() > 0) {

                HttpUrl newBaseUrl;
                HttpUrl oldUrl = request.url();
                builder.removeHeader(KEY_URL_NAME);

                String headerValue = url_name.get(0);
                if (IMInterface.CROP.equals(headerValue)) {

                    newBaseUrl = HttpUrl.parse(TestConfig.cropimage_url);
                } else if (IMInterface.LARGE_IMAGE.equals(headerValue)) {

                    newBaseUrl = HttpUrl.parse(TestConfig.uploadUrl);

                } else {
                    newBaseUrl = oldUrl;
                }

                if (newBaseUrl != null) {
                    HttpUrl httpUrl = oldUrl.newBuilder()
                            .scheme(newBaseUrl.scheme())
                            .host(newBaseUrl.host())
                            .port(newBaseUrl.port())
                            .build();
                    return chain.proceed(builder.url(httpUrl).build());
                }

            }


            return chain.proceed(request);
        };
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(urlInterceptor)
                .build();

        mImInterfcae = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(IMInterface.class);
    }

    private static HttpClient getInstance(String url) {
        synchronized (HttpClient.class) {
            if (mNetClient == null) {
                mNetClient = new HttpClient(url);
            }
        }
        return mNetClient;
    }

    public static IMInterface create(String url) {

        return HttpClient.getInstance(url).mImInterfcae;
    }

}
