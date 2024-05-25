package org.ammonium.smple.sdk.api.model;

import java.util.UUID;
import org.ammonium.smple.sdk.util.GsonProvider;

/**
 * Represents a player on the server (this is a k/v cache of the player's uuid and username)
 *
 * @param uniqueId uuid
 * @param username username
 */
public record User(
    UUID uniqueId,
    String username,
    String displayName
) {

    public static User fromString(String json) {
        return GsonProvider.fromJson(json, User.class);
    }

    public String toJson() {
        return GsonProvider.toJson(this);
    }

}
