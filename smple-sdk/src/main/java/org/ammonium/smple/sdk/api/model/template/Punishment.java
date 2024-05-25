package org.ammonium.smple.sdk.api.model.template;

import java.time.Duration;
import java.util.UUID;
import org.ammonium.smple.sdk.util.GsonProvider;

public interface Punishment {

    static Punishment fromJson(String json) {
        return GsonProvider.fromJson(json, Punishment.class);
    }

    UUID id();

    Type getType();

    String reason();

    Duration duration();

    String toJson();

    enum Type {
        BAN,
        KICK,
        MUTE,
        WARN
    }

}
