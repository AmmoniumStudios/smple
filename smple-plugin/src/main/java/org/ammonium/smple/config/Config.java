package org.ammonium.smple.config;

import lombok.Getter;
import org.ammonium.smple.sdk.config.ConfigManager;
import org.ammonium.smple.sdk.message.FancyMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@Getter
@ConfigSerializable
public final class Config {

    @Setting
    private final String example = "Hello, World!";

    private final FancyMessage chatFormat =
        new FancyMessage("<displayname> <gray>» <reset><message>");

    public static Config get() {
        return ConfigManager.getInstance().getConfig(Config.class);
    }
}
