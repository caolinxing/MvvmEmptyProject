package com.zhulong.network.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * 应用模块:utils
 * <p>
 * 类描述: json解析工具类
 * <p>
 */
public class GsonUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static final Gson S_LOCAL_GSON = createLocalGson();

    private static final Gson S_REMOTE_GSON = createRemoteGson();

    private static GsonBuilder createLocalGsonBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        gsonBuilder.setDateFormat(DATE_FORMAT);
        return gsonBuilder;
    }

    private static Gson createLocalGson() {
        return createLocalGsonBuilder().create();
    }

    private static Gson createRemoteGson() {
        return createLocalGsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public static Gson getLocalGson() {
        return S_LOCAL_GSON;
    }

    public static <T> T fromLocalJson(String json, Class<T> clazz)
            throws JsonSyntaxException {
        try {
            return S_LOCAL_GSON.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromLocalJson(String json, Type typeOfT) {
        return S_LOCAL_GSON.fromJson(json, typeOfT);
    }

    public static String toJson(Object src) {
        return S_LOCAL_GSON.toJson(src);
    }

    public static String toRemoteJson(Object src) {
        return S_REMOTE_GSON.toJson(src);
    }
}
