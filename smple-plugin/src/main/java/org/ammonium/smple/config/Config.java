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
        new FancyMessage("<display_name> <gray>» <reset><message>");
    
    
    @Setting
    private final Sleep sleep = new Sleep();
    
    @ConfigSerializable
    @Getter
    public static final class Sleep {
        
        @Setting
        private final int minPlayersSleeping = 1;
        
        @Setting
        private final int nightSkipDelay = 5;
        
        public static Sleep get() { return ConfigManager.getInstance().getConfig(Sleep.class); }
    }
    

    public static Config get() {
        return ConfigManager.getInstance().getConfig(Config.class);
    }
}
