/*
package com.example.user.nettydemo.test;


import android.support.annotation.NonNull;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JsonFormator {
    private static final JsonFormator ourInstance = new JsonFormator();

    public static JsonFormator getInstance() {
        return ourInstance;
    }

    private JsonFormator() {
    }

    void jsonFormator(List<File> fileList, String json, @NonNull UploadSuccess uploadSuccess) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("code")) {

                int code = (int) jsonObject.get("code");
                if (code == 1) {

                    if (jsonObject.has("data")) {
                        JSONObject data = (JSONObject) jsonObject.get("data");
                        fileList.stream().map((Function<File, List<String>>) file -> {
                            ArrayList<String> rids = new ArrayList<>();
                            if (data.has(file.getName())) {
                                JSONObject object;
                                try {
                                    object = (JSONObject) data.get(file.getName());
                                    if (object.has("rid")) {
                                        String rid = (String) object.get("rid");
                                        rids.add(rid);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            return rids;
                        }).forEach(uploadSuccess::uploadSuccess);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface UploadSuccess {

        void uploadSuccess(List<String> rids);
    }
}
*/
