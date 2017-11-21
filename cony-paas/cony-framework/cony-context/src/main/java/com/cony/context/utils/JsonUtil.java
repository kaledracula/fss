package com.cony.context.utils;

import com.google.gson.*;

import java.util.Map;

public class JsonUtil {
    private static Gson gson = buildGson();

    private static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return gsonBuilder.create();
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static Map<String,String> fromJson(String json, Map<String,String> map) {
        return gson.fromJson(json, map.getClass());
    }

    public static String getPropertyValue(String srcJson, String propertyName) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(srcJson);
            JsonObject jsonObj = element.getAsJsonObject();
            return jsonObj.get(propertyName).toString();
        } catch (Exception e) {
        }
        return null;
    }

    // liyh +
    public static String getArrayPropertyValue(String srcJson, int idx, String propertyName){
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(srcJson);
            JsonArray jsonArray = element.getAsJsonArray();
            if (idx >=0 && idx < jsonArray.size()){
                JsonElement subElement = jsonArray.get(idx);
                return subElement.getAsJsonObject().get(propertyName).getAsString();
            }
        } catch (Exception e) {
        }
        return null;
    }

}
