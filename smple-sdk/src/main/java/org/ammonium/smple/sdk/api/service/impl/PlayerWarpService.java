package org.ammonium.smple.sdk.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.ammonium.smple.sdk.api.model.PlayerWarp;
import org.ammonium.smple.sdk.api.service.Service;

public class PlayerWarpService implements Service<UUID, PlayerWarp> {

    private final Map<UUID, List<PlayerWarp>> cache = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<Void> create(PlayerWarp entity) {
        return runAsync(() -> {
            List<PlayerWarp> warps = this.cache.get(entity.uniqueId());
            if (warps == null) {
                warps = new ArrayList<>();
            }
            warps.add(entity);
            this.cache.put(entity.uniqueId(), warps);
        });
    }

    public CompletableFuture<PlayerWarp> getByName(UUID uniqueId, String name) {
        return supplyAsync(() -> {
            List<PlayerWarp> warps = this.cache.get(uniqueId);
            if (warps == null) {
                return null;
            }
            return warps.stream()
                .filter(warp -> warp.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Warp not found"));
        });
    }

    public CompletableFuture<Void> deleteByName(UUID uniqueId, String name) {
        return runAsync(() -> {
            List<PlayerWarp> warps = this.cache.get(uniqueId);
            if (warps == null) {
                return;
            }
            warps.removeIf(warp -> warp.name().equals(name));
        });
    }

}
