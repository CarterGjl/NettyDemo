/*
package com.example.user.nettydemo.test;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.example.user.nettydemo.test.ImageInfo.ImageInfo;
import com.orhanobut.logger.Logger;
import com.talent.chat.Test.ImageInfo.ImageInfo;
import com.talent.chat.carter.utils.MissUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DownloadManager {
    private static final DownloadManager ourInstance = new DownloadManager();

    public static DownloadManager getInstance() {
        return ourInstance;
    }

    private DownloadManager() {
    }

    @NonNull
    @SuppressLint("CheckResult")
    public Observable<String> downloadFile(@NonNull String rid) {

        return HttpClient.
                create(TestConfig.uploadUrl)
                .downloadFile(rid)
                .subscribeOn(Schedulers.io())
                .map(ResponseBody::byteStream)
                .observeOn(Schedulers.computation())
                .map(inputStream -> writeFile(inputStream, rid))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @SuppressLint("CheckResult")
    public Observable<ImageInfo> downloadCropImage(@NonNull String rid) {

        return HttpClient
                .create(TestConfig.uploadUrl)
                .downloadCropImage(rid)
                .subscribeOn(Schedulers.io())
                .map(ResponseBody::byteStream)
                .observeOn(Schedulers.computation())
                .map(inputStream -> writeImageFile(inputStream, rid))
                .observeOn(AndroidSchedulers.mainThread());

    }

    @NonNull
    private ImageInfo writeImageFile(@NonNull InputStream inputStream, @NonNull String rid) throws IOException {

        String trid = rid + "s";
        String pathByResourceId = MissUtils.getPathByResourceId(trid);
        File file = new File(pathByResourceId);
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setTrid(trid);
        imageInfo.setLrid(rid);
        if (file.exists()) {
//            boolean delete = file.delete();
//            Logger.i("writeFile: "+delete);

            return imageInfo;
        }
        FileOutputStream fos = new FileOutputStream(file);


        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {

            fos.write(bytes, 0, len);

        }

        fos.close();
        inputStream.close();
        Logger.d("====下载成功=====");
        return imageInfo;
    }

    @NonNull
    private String writeFile(@NonNull InputStream inputStream, @NonNull String rid) throws
            IOException {

        String pathByResourceId = MissUtils.getPathByResourceId(rid);
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;

//        +".temp"

        File file = new File(pathByResourceId);
        if (file.exists()) {
//            boolean delete = file.delete();
//            Logger.i("writeFile: "+delete);
            return rid;
        }
        FileOutputStream fos = new FileOutputStream(file);


        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {

            fos.write(bytes, 0, len);

        }
        fos.close();
        inputStream.close();
        Logger.d("====下载成功=====");
        return rid;
    }
}
*/
