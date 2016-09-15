package com.wyf.common.codec;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public final class JsonCodec {
    private static final Gson GSON = new Gson();

    private JsonCodec() {
    }

    public static <T> String encode(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T decode(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    public static <T> T decode(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }
}