package org.ammonium.smple.sdk.api.model;

import java.util.UUID;
import org.ammonium.smple.sdk.util.GsonProvider;

public record PlayerWarp(
    UUID uniqueId,
    String name,
    int x,
    int y,
    int z
) {

    public static PlayerWarp fromJson(String json) {
        return GsonProvider.fromJson(json, PlayerWarp.class);
    }

    public String toJson() {
        return GsonProvider.toJson(this);
    }

}
