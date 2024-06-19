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

    private final FancyMessage chatFormat =
        new FancyMessage("<display_name> <gray>» <reset><message>");
    
  
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
    
    @ConfigSerializable
    @Getter
    public static final class Sleep {
        
        @Setting
        private final int minPlayersSleeping = 1;
        
        @Setting
        private final int nightSkipDelay = 5;
        
        public static Sleep get() { return ConfigManager.getInstance().getConfig(Sleep.class); }
    }
    
    @Setting
    private final Sleep sleep = new Sleep();
    
    
    @ConfigSerializable
    @Getter
    public static final class Warps {
        
        @Setting
        private final int rtpRadius = 1000;
        
        @Setting
        private final int warpDelay = 5;
        
        @Setting
        private final int warpCooldown = 5;
        
        @Setting
        private final int tpRequestTimeout = 30;
        
        @Setting
        private final int maxHomes = 3;
        
        @Setting
        private final int maxPWarps = 3;
        
        
        public static Warps get() { return ConfigManager.getInstance().getConfig(Warps.class); }
    }
    
    @Setting
    private final Warps warps = new Warps();
    

    public static Config get() {
        return ConfigManager.getInstance().getConfig(Config.class);
    }
}
