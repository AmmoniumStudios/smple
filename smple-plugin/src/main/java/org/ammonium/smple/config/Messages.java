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

    @Setting
    private final Teleport teleport = new Teleport();

    @Setting
    private final Home home = new Home();

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

    @ConfigSerializable
    @Getter
    public static class Teleport {
        @Setting
        private final FancyMessage teleport = new FancyMessage("<green>Teleported to <player>!");

        @Setting
        private final FancyMessage teleportRequestSent = new FancyMessage("<green>Teleport request sent to <player>!");

        @Setting
        private final FancyMessage teleportRequestReceived = new FancyMessage("<green><player> has requested to teleport to you. Type /tpaccept to accept");

        @Setting
        private final FancyMessage teleportRequestAccepted = new FancyMessage("<green>Teleport request accepted!");

        @Setting
        private final FancyMessage teleportRequestDenied = new FancyMessage("<red>Teleport request denied!");

        @Setting
        private final FancyMessage teleportRequestExpired = new FancyMessage("<red>Teleport request expired!");

        @Setting
        private final FancyMessage teleportHere = new FancyMessage("<green>Teleported <player> to you!");

        @Setting
        private final FancyMessage teleportHereRequestSent = new FancyMessage("<green>Teleport request sent to <player>!");

        @Setting
        private final FancyMessage teleportHereRequestReceived = new FancyMessage("<green><player> has requested to teleport you to them. Type /tpaccept to accept");

    }

    @ConfigSerializable
    @Getter
    public static class Home {
        @Setting
        private final FancyMessage homeSet = new FancyMessage("<green>Home <name> has been set!");

        @Setting
        private final FancyMessage homeRemoved = new FancyMessage("<green>Home <name> has been removed!");

        @Setting
        private final FancyMessage homeNotFound = new FancyMessage("<red>Home <name> not found!");

        @Setting
        private final FancyMessage homeTeleported = new FancyMessage("<green>Teleported to home <name>!");

        @Setting
        private final FancyMessage homeExists = new FancyMessage("<red>Home <name> already exists!");
    }

    public static Messages get() {
        return ConfigManager.getInstance().getConfig(Messages.class);
    }

}
