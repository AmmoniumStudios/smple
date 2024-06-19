package org.ammonium.smple.sdk.api.service.impl;

import org.ammonium.smple.sdk.api.model.Home;
import org.ammonium.smple.sdk.api.service.Service;
import org.ammonium.smple.sdk.storage.sql.SqlStorageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HomeService implements Service<UUID, Home> {

    private final Map<UUID, List<Home>> cache = new ConcurrentHashMap<>();

    private final SqlStorageFactory storageFactory;

    public HomeService(SqlStorageFactory storageFactory) {
        this.storageFactory = storageFactory;

        this.storageFactory.addTable(
            """
                CREATE TABLE IF NOT EXISTS homes (
                    uuid VARCHAR(36) NOT NULL,
                    name VARCHAR(255) NOT NULL,
                    x INT NOT NULL,
                    y INT NOT NULL,
                    z INT NOT NULL
                );
                """
        );
    }

    @Override
    public CompletableFuture<Void> create(Home entity) {
        return runAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO homes (uuid, name, x, y, z) VALUES (?, ?, ?, ?, ?, ?)")
            ) {
                statement.setString(1, entity.ownerId().toString());
                statement.setString(2, entity.name());
                statement.setInt(3, entity.x());
                statement.setInt(4, entity.y());
                statement.setInt(5, entity.z());
                statement.executeUpdate();

                cache.computeIfAbsent(entity.ownerId(), k -> new CopyOnWriteArrayList<>()).add(entity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<Void> update(Home entity) {
        return runAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE homes SET x = ?, y = ?, z = ? WHERE uuid = ? AND name = ?")
            ) {
                statement.setInt(1, entity.x());
                statement.setInt(2, entity.y());
                statement.setInt(3, entity.z());
                statement.setString(4, entity.ownerId().toString());
                statement.setString(5, entity.name());
                statement.executeUpdate();

                // update cache
                final List<Home> homes = cache.get(entity.ownerId());
                if (homes == null) {
                    throw new IllegalArgumentException("Home not found");
                }

                // Remove the old home
                homes.removeIf(home -> home.name().equals(entity.name()));

                // Add the new home
                homes.add(entity);

                cache.put(entity.ownerId(), homes);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
        return supplyAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM homes WHERE uuid = ?")
            ) {
                statement.setString(1, uniqueId.toString());
                statement.executeQuery();

                final List<Home> homes = new CopyOnWriteArrayList<>();
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        homes.add(new Home(
                            UUID.fromString(resultSet.getString("uuid")),
                            resultSet.getString("name"),
                            resultSet.getInt("x"),
                            resultSet.getInt("y"),
                            resultSet.getInt("z")
                        ));
                    }
                }

                cache.put(uniqueId, homes);
                return homes;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
