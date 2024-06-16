package org.ammonium.smple.config;

import lombok.Getter;
import org.ammonium.smple.sdk.config.ConfigManager;
import org.ammonium.smple.sdk.message.FancyMessage;
import org.ammonium.smple.sdk.storage.credentials.Credentials;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@Getter
@ConfigSerializable
public final class Config {

    @Setting
    private final Credentials credentials = new Credentials();

    @Setting
    private final Chat chat = new Chat();

    @ConfigSerializable
    @Getter
    public static class Chat {

        @Setting
        private final FancyMessage format = new FancyMessage(
            "<display_name> <gray>» <reset><message>"
        );

    }

    public static Config get() {
        return ConfigManager.getInstance().getConfig(Config.class);
    }
}
