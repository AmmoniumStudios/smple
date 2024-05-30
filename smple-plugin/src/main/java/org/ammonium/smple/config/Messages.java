package org.ammonium.smple.config;

import lombok.Getter;
import org.ammonium.smple.sdk.config.ConfigManager;
import org.ammonium.smple.sdk.message.FancyMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@Getter
@ConfigSerializable
public final class Messages {

    @Setting
    private final FancyMessage prefix = new FancyMessage("<aqua><bold>Smple <gray>» <reset>");

    @Setting
    private final FancyMessage banMessage = new FancyMessage(
        "<red>You are banned from this server for <reason>!"
    );

    @Setting
    private final FancyMessage muted = new FancyMessage(
        "<red>You are muted for <reason>. Expires in: <duration> "
    );

    @Setting
    private final FancyMessage smeltSuccessful = new FancyMessage(
        prefix + "<green>Successfully smelted <item> x<amount>!"
    );

    public static Messages get() {
        return ConfigManager.getInstance().getConfig(Messages.class);
    }

}
