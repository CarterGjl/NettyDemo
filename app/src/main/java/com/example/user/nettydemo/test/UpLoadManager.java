/*
package com.example.user.nettydemo.test;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.talentframework.talentexception.CoreException;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UpLoadManager {

    private static UpLoadManager mUpLoadManager = null;


    private UpLoadManager() {
    }

    public static UpLoadManager getInstance() {
        synchronized (UpLoadManager.class) {
            if (mUpLoadManager == null) {
                mUpLoadManager = new UpLoadManager();
            }
        }
        return mUpLoadManager;
    }

    void uploadMuiltyFile(@NonNull List<String> fileNames) {

        List<File> fileList = new ArrayList<>();
        fileNames.stream().flatMap((Function<String, Stream<File>>) s -> {


            File file = new File(s);
            fileList.add(file);
            return fileList.stream();
        }).map(file -> {
            List<MultipartBody.Part> parts = new ArrayList<>();
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData(file.getName(), file
                    .getName(), requestBody);
            parts.add(body);
            return parts;
        }).forEach(parts ->
                HttpClient.create(TestConfig.uploadUrl)
                        .uploadFile(parts)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(responseBody -> JsonFormator.getInstance()
                                .jsonFormator(fileList, responseBody.string(), rids -> {


                                }), throwable -> {

                        }));
    }

    */
/* Environment.getExternalStorageDirectory().getAbsolutePath()
                 +File.separator+"qjd_cp.jpg"*//*

    @NonNull
    public Observable<FileInfo> uploadFile(@NonNull String filePath) {

        String mName;

        File file = new File(filePath);
        mName = file.getName();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part data = MultipartBody.Part.createFormData(file.getName(), file.getName
                (), requestBody);

        return HttpClient.create(TestConfig.uploadUrl)
                .uploadFile(data).map(responseBody -> {

                    JSONObject jsonObject;
                    if (responseBody != null) {
                        jsonObject = new JSONObject(responseBody.string());
                        int code = (int) jsonObject.get("code");
                        JSONObject data1 = (JSONObject) jsonObject.get("data");
                        JSONObject name = (JSONObject) data1.get(mName);
                        String rid = (String) name.get("rid");
                        Logger.d(rid);
                        if (rid != null) {

                            FileInfo fileInfo = new FileInfo();
                            fileInfo.setFileSize((int) file.length());
                            fileInfo.setRid(rid);
                            return fileInfo;
                        } else {
                            throw new CoreException(123, "上传失败");
                        }

                    }
                    throw new CoreException(123, "上传失败");
                });


    }

   */
/* public interface UploadSuccess{

        void uploadSuccess(String rid);
    }

    public interface UploadError{

        void uploadError() throws CoreException;
    }*//*

}
*/
