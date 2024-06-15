package org.ammonium.smple.sdk.storage.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.ammonium.smple.sdk.storage.StorageFactory;
import org.ammonium.smple.sdk.storage.credentials.Credentials;

public final class SqlStorageFactory implements StorageFactory<Connection> {

    private HikariDataSource dataSource;

    @Override
    public void setup(Credentials credentials) {
        final HikariConfig config = new HikariConfig();

        final String jdbcUrl = String.format("postgresql://%s:%d/%s",
            credentials.getHost(),
            credentials.getPort(),
            credentials.getDatabase()
        );

        config.setJdbcUrl(jdbcUrl);
        config.setUsername(credentials.getUsername());
        config.setPassword(credentials.getPassword());

        credentials.getConfigurationOptions().forEach(config::addDataSourceProperty);

        this.dataSource = new HikariDataSource(config);
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
}
