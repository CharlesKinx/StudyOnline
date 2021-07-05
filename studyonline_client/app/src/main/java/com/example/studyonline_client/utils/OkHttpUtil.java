package com.example.studyonline_client.utils;

import com.alibaba.fastjson.JSON;
import com.example.studyonline_client.model.WorkInfo;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {

    private static OkHttpClient okHttpClient;
    private static final int TIME_OUT = 5; //超时时间

    static {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        okHttpClient = okHttpBuilder.build();
    }

    private static Request getRequest(String url,String json){
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"),json))
                .build();

    }

    private static Request getRequest(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }

    public static Call usePost(String url,String json){
        Call call = okHttpClient.newCall(getRequest(url,json));
        return call;
    }

    public static Call useGet(String url){
        Call call = okHttpClient.newCall(getRequest(url));
        return call;
    }

    public static Call usePostById(String url,int id){
        Map<String,Integer> map = new HashMap<>();
        map.put("id",id);
        String json = JSON.toJSONString(map);
        System.out.println(json);
        Call call = usePost(url,json);
        return call;
    }


    public static Call usePostUploadFile(String url, WorkInfo workInfo,File file){

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("classId", String.valueOf(workInfo.getClassId()))
                .addFormDataPart("studentId", String.valueOf(workInfo.getStudentId()))
                .addFormDataPart("type", workInfo.getFileType())
                .addFormDataPart("sourceName", workInfo.getFileName())
                .addFormDataPart("source",
                        String.valueOf(new Random().nextInt()) + "." + workInfo.getFileName().split("\\.")[1],
                        RequestBody.create(mediaType, file)
                )
                .build();


        Request request = new Request.Builder().post(requestBody).url(url).build();

        Call call = okHttpClient.newCall(request);
        return call;
    }

}
