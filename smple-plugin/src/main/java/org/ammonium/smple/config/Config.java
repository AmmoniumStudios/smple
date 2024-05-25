package org.ammonium.smple.config;

import org.ammonium.smple.sdk.config.ConfigManager;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public final class Config {

    @Setting
    private String example = "Hello, World!";

    public static Config get() {
        return ConfigManager.getInstance().getConfig(Config.class);
    }

    public String getExample() {
        return example;
    }
}
