package org.ammonium.smple.sdk.api.model.punish;

import org.ammonium.smple.sdk.api.model.template.Punishment;
import org.ammonium.smple.sdk.util.GsonProvider;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public record Kick(String reason, UUID id, Instant time) implements Punishment {

    @Override
    public Type getType() {
        return Type.KICK;
    }

    @Override
    public Duration duration() {
        return Duration.ZERO;
    }

    @Override
    public String toJson() {
        return GsonProvider.toJson(this);
    }
}
