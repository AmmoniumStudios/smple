package org.ammonium.smple.config;

import lombok.Getter;
import org.ammonium.smple.sdk.config.ConfigManager;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@Getter
@ConfigSerializable
public final class Sleep {
    
    @Setting
    private final int minPlayersSleeping = 1;
    
    @Setting
    private final int nightSkipDelay = 5;
    
    public static Sleep get() { return ConfigManager.getInstance().getConfig(Sleep.class); }
    
}
