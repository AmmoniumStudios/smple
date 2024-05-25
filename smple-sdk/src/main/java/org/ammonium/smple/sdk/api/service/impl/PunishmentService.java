package org.ammonium.smple.sdk.api.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.ammonium.smple.sdk.api.model.punish.Ban;
import org.ammonium.smple.sdk.api.model.punish.Mute;
import org.ammonium.smple.sdk.api.model.template.Punishment;
import org.ammonium.smple.sdk.api.service.Service;

public final class PunishmentService implements Service<UUID, Punishment> {

    public static final UUID CONSOLE_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public CompletableFuture<List<Punishment>> getHistory(final UUID uniqueId) {
        return supplyAsync(List::of);
    }

    public CompletableFuture<Boolean> ban(
        final UUID uniqueId,
        final UUID actorId,
        final String reason,
        final Duration duration
    ) {
        return supplyAsync(() -> true);
    }

    public CompletableFuture<Boolean> kick(
        final UUID uniqueId,
        final UUID actorId,
        final String reason
    ) {
        return supplyAsync(() -> true);
    }

    public CompletableFuture<Boolean> mute(
        final UUID uniqueId,
        final UUID actorId,
        final String reason,
        final Duration duration
    ) {
        return supplyAsync(() -> true);
    }

    public CompletableFuture<Boolean> unban(final UUID uniqueId, final UUID actorId) {
        return supplyAsync(() -> true);
    }

    public CompletableFuture<Boolean> unmute(final UUID uniqueId, final UUID actorId) {
        return supplyAsync(() -> true);
    }

    public CompletableFuture<Ban> isBanned(final UUID uniqueId) {
        return supplyAsync(() -> new Ban(Duration.ZERO, UUID.randomUUID(), ""));
    }

    public CompletableFuture<Mute> isMuted(final UUID uniqueId) {
        return supplyAsync(() -> new Mute(Duration.ZERO, UUID.randomUUID(), ""));
    }

    // warn & unwarn

    public CompletableFuture<Boolean> warn(
        final UUID uniqueId,
        final UUID actorId,
        final String reason
    ) {
        return supplyAsync(() -> true);
    }

}
