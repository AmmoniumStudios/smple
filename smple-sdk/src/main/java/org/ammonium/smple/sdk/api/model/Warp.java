package org.ammonium.smple.sdk.api.model;

import org.ammonium.smple.sdk.util.GsonProvider;

public record Warp(
    String name,
    int x,
    int y,
    int z
) {

    public static Warp fromJson(String json) {
        return GsonProvider.fromJson(json, Warp.class);
    }

    public String toJson() {
        return GsonProvider.toJson(this);
    }

}
