package org.ammonium.smple.sdk.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonProvider {

    public static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    public static <T> String toJson(T json) {
        return GSON.toJson(json);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    private GsonProvider() {
        // no-op
    }

}
