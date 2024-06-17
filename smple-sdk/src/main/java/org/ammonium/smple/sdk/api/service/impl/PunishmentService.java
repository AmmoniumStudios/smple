package org.ammonium.smple.sdk.api.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.ammonium.smple.sdk.api.model.punish.Ban;
import org.ammonium.smple.sdk.api.model.punish.Mute;
import org.ammonium.smple.sdk.api.model.template.Punishment;
import org.ammonium.smple.sdk.api.service.Service;
import org.ammonium.smple.sdk.storage.sql.SqlStorageFactory;

public final class PunishmentService implements Service<UUID, Punishment> {

    public static final UUID CONSOLE_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private final SqlStorageFactory storageFactory;

    public PunishmentService(SqlStorageFactory storageFactory) {
        this.storageFactory = storageFactory;

        this.storageFactory.addTable(
            """
            CREATE TABLE IF NOT EXISTS bans (
                uuid VARCHAR(36) PRIMARY KEY,
                actor VARCHAR(36) NOT NULL,
                reason TEXT NOT NULL,
                duration BIGINT NOT NULL,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                active BOOLEAN NOT NULL
            );
            """
        );

        this.storageFactory.addTable(
            """
            CREATE TABLE IF NOT EXISTS kicks (
                uuid VARCHAR(36) PRIMARY KEY,
                actor VARCHAR(36) NOT NULL,
                reason TEXT NOT NULL,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """
        );

        this.storageFactory.addTable(
            """
            CREATE TABLE IF NOT EXISTS mutes (
                uuid VARCHAR(36) PRIMARY KEY,
                actor VARCHAR(36) NOT NULL,
                reason TEXT NOT NULL,
                duration BIGINT NOT NULL,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                active BOOLEAN NOT NULL
            );
            """
        );

        this.storageFactory.addTable(
            """
            CREATE TABLE IF NOT EXISTS warns (
                uuid VARCHAR(36) PRIMARY KEY,
                actor VARCHAR(36) NOT NULL,
                reason TEXT NOT NULL
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """
        );
    }

    public CompletableFuture<List<Punishment>> getHistory(final UUID uniqueId) {
        return supplyAsync(List::of);
    }

    public CompletableFuture<Void> ban(
        final UUID uniqueId,
        final UUID actorId,
        final String reason,
        final Duration duration
    ) {
        return supplyAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO bans (uuid, actor, reason, duration, active) VALUES (?, ?, ?, ?, ?)")
            ) {
                statement.setString(1, uniqueId.toString());
                statement.setString(2, actorId.toString());
                statement.setString(3, reason);
                if (duration == null) {
                    // -1 means permanent
                    statement.setLong(4, -1);
                } else {
                    // duration is in milliseconds (1 second = 1000 milliseconds
                    statement.setLong(4, duration.toMillis());
                    statement.setBoolean(5, true);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    public CompletableFuture<Boolean> kick(
        final UUID uniqueId,
        final UUID actorId,
        final String reason
    ) {
        return supplyAsync(() -> {
            try {
                try (
                    Connection connection = this.storageFactory.getConnection();
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO kicks (uuid, actor, reason) VALUES (?, ?, ?)")
                ) {
                    statement.setString(1, uniqueId.toString());
                    statement.setString(2, actorId.toString());
                    statement.setString(3, reason);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;

        });
    }

    public CompletableFuture<Boolean> mute(
        final UUID uniqueId,
        final UUID actorId,
        final String reason,
        final Duration duration
    ) {
        return supplyAsync(() -> {
            try {
                try (
                    Connection connection = this.storageFactory.getConnection();
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO mutes (uuid, actor, reason, duration, active) VALUES (?, ?, ?, ?, ?)")
                ) {
                    statement.setString(1, uniqueId.toString());
                    statement.setString(2, actorId.toString());
                    statement.setString(3, reason);
                    statement.setLong(4, duration.toMillis());
                    statement.setBoolean(5, true);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;

        });
    }

    public CompletableFuture<Boolean> unban(final UUID uniqueId, final UUID actorId) {
        return supplyAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE bans SET active = false WHERE uuid = ? AND active = true")
            ) {
                statement.setString(1, uniqueId.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;

        });
    }

    public CompletableFuture<Boolean> unmute(final UUID uniqueId, final UUID actorId) {
        return supplyAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE mutes SET active = false WHERE uuid = ? AND active = true")
            ) {
                statement.setString(1, uniqueId.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
    }

    public CompletableFuture<Boolean> warn(
        final UUID uniqueId,
        final UUID actorId,
        final String reason
    ) {
        return supplyAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO warns (uuid, actor, reason) VALUES (?, ?, ?)")
            ) {
                statement.setString(1, uniqueId.toString());
                statement.setString(2, actorId.toString());
                statement.setString(3, reason);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
    }

    public CompletableFuture<Ban> isBanned(final UUID uniqueId) {
        return supplyAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM bans WHERE uuid = ? AND active = true LIMIT 1")
            ) {
                statement.setString(1, uniqueId.toString());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Ban(
                            Duration.ofMillis(resultSet.getLong("duration")),
                            UUID.fromString(resultSet.getString("uuid")),
                            resultSet.getString("reason"),
                            resultSet.getTimestamp("timestamp").toInstant()
                        );
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    public CompletableFuture<Mute> isMuted(final UUID uniqueId) {
        return supplyAsync(() -> {
            try (
                Connection connection = this.storageFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM mutes WHERE uuid = ? AND active = true LIMIT 1")
            ) {
                statement.setString(1, uniqueId.toString());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Mute(
                            Duration.ofMillis(resultSet.getLong("duration")),
                            UUID.fromString(resultSet.getString("uuid")),
                            resultSet.getString("reason"),
                            resultSet.getTimestamp("timestamp").toInstant()
                        );
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

}
