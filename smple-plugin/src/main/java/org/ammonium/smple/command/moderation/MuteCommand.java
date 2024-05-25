package org.ammonium.smple.command.moderation;

import java.time.Duration;
import java.util.UUID;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotation.specifier.Greedy;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class MuteCommand {

    private final PunishmentService punishmentService;

    public MuteCommand(PunishmentService punishmentService) {
        this.punishmentService = punishmentService;
    }

    @Command("mute <target> <duration> <reason>")
    @Permission("smple.moderation.mute")
    public void mute(
        final CommandSender sender,
        @Argument("target") final String target,
        @Argument("duration") final Duration duration,
        @Greedy @Argument("reason") final String reason
    ) {
        final UUID senderId = sender instanceof Player
            ? ((Player) sender).getUniqueId()
            : PunishmentService.CONSOLE_ID;

        final OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayerIfCached(target);
        if (offlinePlayer == null) {
            sender.sendMessage("Player not found.");
            return;
        }

        punishmentService.mute(
            offlinePlayer.getUniqueId(),
            senderId,
            reason,
            duration
        ).thenAccept(success -> {
            if (success) {
                sender.sendMessage("Player muted.");
            } else {
                sender.sendMessage("Failed to mute player.");
            }
        });

    }

    @Command("unmute <target>")
    @Permission("smple.moderation.unmute")
    @CommandDescription("Unmute a player")
    public void unmute(
        final CommandSender sender,
        @Argument("target") final String target
    ) {
        final UUID senderId = sender instanceof Player
            ? ((Player) sender).getUniqueId()
            : PunishmentService.CONSOLE_ID;

        final OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayerIfCached(target);
        if (offlinePlayer == null) {
            sender.sendMessage("Player not found.");
            return;
        }

        punishmentService.unmute(
            offlinePlayer.getUniqueId(),
            senderId
        ).thenAccept(success -> {
            if (success) {
                sender.sendMessage("Player unmuted.");
            } else {
                sender.sendMessage("Failed to unmute player.");
            }
        });
    }



}
