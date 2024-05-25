package org.ammonium.smple.sdk.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.ConfigurateException;

public final class ConfigManager implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger("smple-sdk/config");
    private static ConfigManager instance;

    public static ConfigManager getInstance() {
        return instance;
    }

    private final Map<Class<?>, ConfigHandler<?>> handlers = new ConcurrentHashMap<>();

    private final Path directory;
    private final Plugin plugin;

    private ConfigManager(final Plugin plugin) {
        this.plugin = plugin;
        this.directory = plugin.getDataFolder().toPath();

        if (!this.directory.toFile().exists()) {
            this.directory.toFile().mkdirs();
        }

        ConfigManager.instance = this;
    }


    @Override
    public void close() {
        for (ConfigHandler<?> configHandler : this.handlers.values()) {
            try {
                configHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            ConfigHandler.FILE_WATCHER.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveConfig(Class<?> config) {
        try {
            this.handlers.get(config).saveToFile();
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public void initConfigs(Plugin plugin, Class<?>... configs) {
        for (Class<?> config : configs) {
            initConfig(this.directory.resolve(plugin.getName()), config);
        }
    }

    public void initConfigs(Path dir, Class<?>... configs) {
        for (Class<?> config : configs) {
            initConfig(dir, config);
        }
    }

    private void initConfig(Path dir, Class<?> config) {
        if (!dir.toFile().exists()) {
            dir.toFile().mkdirs();
        }
        LOGGER.info("Initialising Configuration: {}/{}", dir.getFileName().toString(), config.getSimpleName());
        String fileName = config.getSimpleName().toLowerCase() + ".yml";
        this.handlers.put(config, new ConfigHandler<>(dir, fileName, config));
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfig(Class<T> config) {
        return (T) this.handlers.get(config).getConfig();
    }
}
