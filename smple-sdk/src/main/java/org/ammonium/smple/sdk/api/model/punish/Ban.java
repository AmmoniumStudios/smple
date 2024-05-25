package org.ammonium.smple.sdk.api.model.punish;

import java.time.Duration;
import java.util.UUID;
import org.ammonium.smple.sdk.api.model.template.Punishment;
import org.ammonium.smple.sdk.util.GsonProvider;

public record Ban(Duration duration, UUID id, String reason) implements Punishment {

    @Override
    public Type getType() {
        return Type.BAN;
    }

    @Override
    public String toJson() {
        return GsonProvider.toJson(this);
    }
}
