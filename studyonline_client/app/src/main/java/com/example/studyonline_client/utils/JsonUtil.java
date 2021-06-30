package com.example.studyonline_client.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.util.Map;

public class JsonUtil {
    private static Gson gson;

    static {
        gson = new Gson();
    }


    public static String objectToJson(Object model){
        return gson.toJson(model);
    }

    public static String mapToJson(Map<String,String> map){
        return JSON.toJSONString(map);
    }


}
