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

    @Setting
    private final Warp warp = new Warp();

    @ConfigSerializable
    @Getter
    public static class Warp {

        @Setting
        private final FancyMessage warpSet = new FancyMessage("<green>Warp <name> has been set!");

        @Setting
        private final FancyMessage warpRemoved = new FancyMessage("<green>Warp <name> has been removed!");

        @Setting
        private final FancyMessage warpNotFound = new FancyMessage("<red>Warp <name> not found!");

        @Setting
        private final FancyMessage warpTeleported = new FancyMessage("<green>Teleported to warp <name>!");
    }

    public static Messages get() {
        return ConfigManager.getInstance().getConfig(Messages.class);
    }

}
