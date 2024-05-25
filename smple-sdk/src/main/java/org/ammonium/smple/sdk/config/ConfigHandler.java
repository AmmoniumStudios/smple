package org.ammonium.smple.sdk.config;

import java.io.IOException;
import java.nio.file.Path;
import org.ammonium.smple.sdk.config.serializer.ComponentSerializer;
import org.ammonium.smple.sdk.config.serializer.DurationSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.ValueReference;
import org.spongepowered.configurate.reference.WatchServiceListener;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

public final class ConfigHandler<T> implements AutoCloseable {

    public static final WatchServiceListener FILE_WATCHER;
    private static final Logger LOGGER = LoggerFactory.getLogger("smple-skd/config-handler");

    static {
        try {
            FILE_WATCHER = WatchServiceListener.create();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create file watcher", e);
        }
    }

    private final ConfigurationReference<CommentedConfigurationNode> node;
    private final ValueReference<T, CommentedConfigurationNode> baseRef;

    private final Class<T> configClass;
    private final Path configFile;

    public ConfigHandler(Path folder, String fileName, Class<T> configClass) {
        this.configClass = configClass;
        this.configFile = folder.resolve(fileName);

        try {
            this.node = ConfigHandler.FILE_WATCHER.listenToConfiguration(file ->
                    YamlConfigurationLoader.builder()
                        .defaultOptions(options ->
                            options.shouldCopyDefaults(true)
                                .serializers((serializers) -> serializers
                                    .register(DurationSerializer.get())
                                    .register(ComponentSerializer.get())
                                )
                        )
                        .nodeStyle(NodeStyle.BLOCK)
                        .indent(2)
                        .path(file)
                        .build()
                , configFile);

            ConfigHandler.FILE_WATCHER.listenToFile(this.configFile, event -> {
                LOGGER.info("Updated configuration file: {}", event.context().toString());
                if (getConfig() instanceof Config config) {
                    config.onUpdate().accept(event);
                }
            });


            this.baseRef = this.node.referenceTo(configClass);
            this.node.save();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create configuration loader", e);
        }

    }

    public T getConfig() {
        return this.baseRef.get();
    }

    public void saveToFile() throws ConfigurateException {
        this.node.node().set(configClass, configClass.cast(getConfig()));
        this.node.loader().save(this.node.node());
    }

    @Override
    public void close() {
        this.node.close();
    }
}
