package org.ammonium.smple.sdk.api.model.punish;

import org.ammonium.smple.sdk.api.model.template.Punishment;
import org.ammonium.smple.sdk.util.GsonProvider;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public record Mute(Duration duration, UUID id, String reason, Instant time) implements Punishment {

    @Override
    public Type getType() {
        return Type.MUTE;
    }

    @Override
    public String toJson() {
        return GsonProvider.toJson(this);
    }
}
