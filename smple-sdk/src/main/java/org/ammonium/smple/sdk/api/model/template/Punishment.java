package org.ammonium.smple.sdk.api.model.template;

import org.ammonium.smple.sdk.util.GsonProvider;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public interface Punishment {

    static Punishment fromJson(String json) {
        return GsonProvider.fromJson(json, Punishment.class);
    }

    UUID id();

    Type getType();

    String reason();

    Duration duration();

    Instant time();

    String toJson();

    enum Type {
        BAN,
        KICK,
        MUTE,
        WARN
    }

}
