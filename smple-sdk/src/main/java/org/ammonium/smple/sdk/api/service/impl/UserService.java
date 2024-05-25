package org.ammonium.smple.sdk.api.service.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.ammonium.smple.sdk.api.model.User;
import org.ammonium.smple.sdk.api.service.Service;

public final class UserService implements Service<UUID, User> {

    private final Map<UUID, User> cache = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<Void> create(User entity) {
        return runAsync(() -> cache.put(entity.uniqueId(), entity));
    }

    @Override
    public CompletableFuture<User> get(UUID uuid) {
        return supplyAsync(() -> cache.get(uuid));
    }

    @Override
    public CompletableFuture<Void> update(User entity) {
        return runAsync(() -> cache.put(entity.uniqueId(), entity));
    }

    @Override
    public CompletableFuture<Void> delete(UUID uuid) {
        return runAsync(() -> cache.remove(uuid));
    }
}
