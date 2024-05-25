package org.ammonium.smple.sdk.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.ammonium.smple.sdk.api.model.Warp;
import org.ammonium.smple.sdk.api.service.Service;

public class WarpService implements Service<UUID, Warp> {

    private final List<Warp> warps = new ArrayList<>();

    @Override
    public CompletableFuture<Void> create(Warp entity) {
        return CompletableFuture.runAsync(() -> warps.add(entity));
    }

    public CompletableFuture<List<Warp>> getAll() {
        return supplyAsync(() -> warps);
    }

    public CompletableFuture<Void> delete(String warp) {
        return CompletableFuture.runAsync(() -> warps.removeIf(w -> w.name().equals(warp)));
    }

    public CompletableFuture<Warp> get(String warp) {
        return supplyAsync(() -> warps.stream()
            .filter(w -> w.name().equals(warp))
            .findFirst().orElse(null)
        );
    }

    public CompletableFuture<Void> update(Warp entity) {
        return runAsync(() -> {
            warps.removeIf(w -> w.name().equals(entity.name()));
            warps.add(entity);
        });
    }
}
