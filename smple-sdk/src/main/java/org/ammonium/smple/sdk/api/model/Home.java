package org.ammonium.smple.sdk.api.model;

import java.util.UUID;
import org.ammonium.smple.sdk.util.GsonProvider;

public record Home(
    UUID ownerId,
    String name,
    int x,
    int y,
    int z
) {

    public static Home fromString(String json) {
        return GsonProvider.fromJson(json, Home.class);
    }

    public String toJson() {
        return GsonProvider.toJson(this);
    }


}
