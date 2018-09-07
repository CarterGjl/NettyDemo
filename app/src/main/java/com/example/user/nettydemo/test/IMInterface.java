package com.example.user.nettydemo.test;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IMInterface {


    /**
     * 登录im
     * @param loginMessage 登录信息
     * @return s
     */
    @Headers({"Referer: http://dev.9spirit.cn:9099/websocket/network/index" +
            ".html?account=5b619411f8d70463e79012cb&service=gwtuitionroom&terminal=1&imapitoken" +
            "=5b8f8e0cf8d7040b39827b34", "imapitoken:5b90d62cf8d7040b39827b5c"})
    @POST("rest/acuim/login")
    Observable<Result<Data>> login(@Body LoginMessage loginMessage);

    @POST("rest/resource")
    @Multipart
    Observable<ResponseBody> uploadFile(@Part MultipartBody.Part file);

    @POST("rest/resource")
    @Multipart
    Observable<ResponseBody> uploadFile(@Part List<MultipartBody.Part> file);


    @Headers({"url_name:"+CROP})
    @GET("unsafe/283x283/http://192.168.1.196/rest/resource/temp/{rid}")
    Observable<ResponseBody> downloadCropImage(@Path("rid") String rid);
    /**
     * 下载文件
     * @param rid 需要下载的资源id
     * @return s
     */
    @Headers({KEY_URL_NAME+LARGE_IMAGE})
    @GET("rest/resource/temp/{rid}")
    Observable<ResponseBody> downloadFile(@Path("rid") String rid);

    @POST("rest/resource")
    @Multipart
    Call<ResponseBody> uploadFile1(@Part List<MultipartBody.Part> file);

    String CROP = "crop";
    String LARGE_IMAGE = "large_image";
    String KEY_URL_NAME = "url_name:";
}
