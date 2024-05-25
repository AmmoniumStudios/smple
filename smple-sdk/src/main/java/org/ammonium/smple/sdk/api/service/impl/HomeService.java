package org.ammonium.smple.sdk.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.ammonium.smple.sdk.api.model.Home;
import org.ammonium.smple.sdk.api.service.Service;

public class HomeService implements Service<UUID, Home> {

    private final Map<UUID, List<Home>> cache = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<Void> create(Home entity) {
        return runAsync(() -> {
            final List<Home> homes = cache.getOrDefault(entity.ownerId(), List.of());
            homes.add(entity);
            cache.put(entity.ownerId(), homes);
        });
    }

    @Override
    public CompletableFuture<Void> update(Home entity) {
        return runAsync(() -> {
            final List<Home> homes = cache.get(entity.ownerId());
            if (homes == null) {
                throw new IllegalArgumentException("Home not found");
            }
            final int index = homes.indexOf(entity);
            if (index == -1) {
                throw new IllegalArgumentException("Home not found");
            }
            homes.set(index, entity);
        });
    }

    public CompletableFuture<Home> getByName(final UUID uniqueId, final String homeName) {
        return supplyAsync(() -> {
            final List<Home> homes = cache.get(uniqueId);
            if (homes == null) {
                throw new IllegalArgumentException("Home not found");
            }

            return homes.stream()
                .filter(home -> home.name().equals(homeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Home not found"));
        });
    }

    public CompletableFuture<Void> delete(final UUID uniqueId, final Home entity) {
        return runAsync(() -> {
            final List<Home> homes = cache.get(uniqueId);
            if (homes == null) {
                throw new IllegalArgumentException("Home not found");
            }
            homes.remove(entity);
        });
    }

    public CompletableFuture<List<Home>> getAll(final UUID uniqueId) {
        return supplyAsync(() -> cache.get(uniqueId));
    }


}
