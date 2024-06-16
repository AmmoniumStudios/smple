package org.ammonium.smple.sdk.api.service.impl;

import org.ammonium.smple.sdk.api.model.User;
import org.ammonium.smple.sdk.api.service.Service;
import org.ammonium.smple.sdk.storage.sql.SqlStorageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public final class UserService implements Service<UUID, User> {

    private final Map<UUID, User> cache = new ConcurrentHashMap<>();
    private final SqlStorageFactory storageFactory;

    public UserService(SqlStorageFactory storageFactory) {
        this.storageFactory = storageFactory;

        this.storageFactory.addTable(
            """
                CREATE TABLE IF NOT EXISTS users (
                    uuid VARCHAR(36) PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    displayName VARCHAR(255) NOT NULL
                );
                """
        );
    }


    @Override
    public CompletableFuture<Void> create(User entity) {
        return runAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (uuid, name, displayName) VALUES (?, ?, ?)")
            ) {
                statement.setString(1, entity.uniqueId().toString());
                statement.setString(2, entity.username());
                statement.setString(3, entity.displayName());
                statement.executeUpdate();

                this.cache.put(entity.uniqueId(), entity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<User> get(UUID uuid) {
        return supplyAsync(() -> {
            User user = cache.get(uuid);
            if (user == null) {
                try (
                    Connection connection = this.storageFactory.getConnection();
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE uuid = ?")
                ) {
                    statement.setString(1, uuid.toString());
                    statement.executeQuery();

                    try (ResultSet resultSet = statement.getResultSet()) {
                        if (resultSet.next()) {
                            user = new User(
                                UUID.fromString(resultSet.getString("uuid")),
                                resultSet.getString("name"),
                                resultSet.getString("displayName")
                            );
                            cache.put(user.uniqueId(), user);
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return user;
        });
    }

    public CompletableFuture<User> loadUser(UUID uniqueId, String username) {
        return get(uniqueId).thenCompose(user -> {
            if (user == null) {
                return create(new User(uniqueId, username, username)).thenApply(v -> new User(uniqueId, username, username));
            }
            return CompletableFuture.completedFuture(user);
        });
    }

    @Override
    public CompletableFuture<Void> update(User entity) {
        return runAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, displayName = ? WHERE uuid = ?")
            ) {
                statement.setString(1, entity.username());
                statement.setString(2, entity.displayName());
                statement.setString(3, entity.uniqueId().toString());
                statement.executeUpdate();

                this.cache.remove(entity.uniqueId());
                this.cache.put(entity.uniqueId(), entity);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<Void> delete(UUID uuid) {
        return runAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE uuid = ?")
            ) {
                statement.setString(1, uuid.toString());
                statement.executeUpdate();

                this.cache.remove(uuid);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
