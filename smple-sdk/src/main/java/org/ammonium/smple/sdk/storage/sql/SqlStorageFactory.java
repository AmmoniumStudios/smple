package org.ammonium.smple.sdk.storage.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.ammonium.smple.sdk.SmpleSdk;
import org.ammonium.smple.sdk.api.service.Service;
import org.ammonium.smple.sdk.storage.StorageFactory;
import org.ammonium.smple.sdk.storage.credentials.Credentials;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public final class SqlStorageFactory implements StorageFactory<Connection> {

    private HikariDataSource dataSource;
    private final Set<String> tables = new HashSet<>();

    @Override
    public void setup(Credentials credentials) {
        final HikariConfig config = new HikariConfig();

        final String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s",
            credentials.getHost(),
            credentials.getPort(),
            credentials.getDatabase()
        );


        config.setJdbcUrl(jdbcUrl);
        config.setUsername(credentials.getUsername());
        config.setPassword(credentials.getPassword());

        config.setDriverClassName("org.postgresql.Driver");

        credentials.getConfigurationOptions().forEach(config::addDataSourceProperty);

        this.dataSource = new HikariDataSource(config);

        Service.EXECUTOR.execute(this::setupTables);
    }

    @Override
    public Connection getConnection() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get connection", e);
        }
    }

    @Override
    public void close() {
        try {
            this.dataSource.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to close connection", e);
        }
    }

    @Override
    public Set<String> getTables() {
        return this.tables;
    }

    public void addTable(String table) {
        this.tables.add(table);
    }

    private void setupTables() {
        try (
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
        ) {
            for (String table : this.tables) {
                statement.addBatch(table);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
