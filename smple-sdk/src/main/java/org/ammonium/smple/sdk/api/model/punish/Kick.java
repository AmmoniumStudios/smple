package org.ammonium.smple.sdk.api.model.punish;

import java.time.Duration;
import java.util.UUID;
import org.ammonium.smple.sdk.api.model.template.Punishment;
import org.ammonium.smple.sdk.util.GsonProvider;

public record Kick(String reason, UUID id) implements Punishment {

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
