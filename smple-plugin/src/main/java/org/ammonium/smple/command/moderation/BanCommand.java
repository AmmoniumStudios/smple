package org.ammonium.smple.command.moderation;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.sdk.SmpleSdk;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.Default;
import org.incendo.cloud.annotations.Permission;

import java.time.Duration;
import java.util.UUID;

public class BanCommand {

    private final SmpleSdk sdk;

    public BanCommand(SmplePlugin bootstrapper) {
        this.sdk = bootstrapper.getSdk();
    }

    @Command("ban <target> <reason> <duration> [silent]")
    @Permission("smple.moderation.ban.run")
    public void ban(
        final CommandSender sender,
        @Argument("target") final String target,
        @Argument("reason") final String reason,
        @Argument("duration") final Duration duration,
        @Argument("silent") @Default("false") final Boolean silent // make this a parameter [-s]
    ) {
        final UUID senderId = sender instanceof Player
            ? ((Player) sender).getUniqueId()
            : PunishmentService.CONSOLE_ID;

        Player targetPlayer;

        if (Bukkit.getPlayer(target) != null) {
            targetPlayer = Bukkit.getPlayer(target);
        } else {
            targetPlayer = Bukkit.getOfflinePlayer(target).getPlayer();
        }

        if (targetPlayer == null) {
            sender.sendMessage("Player not found.");
            return;
        }

        this.sdk.getPunishmentService()
            .ban(targetPlayer.getUniqueId(), senderId, reason, duration)
            .thenAccept(a -> {
                if (targetPlayer.isOnline()) {
                    targetPlayer.kick();
                }

                if (!silent) {
                    // Broadcast ban message to server
                } else {
                    // Send message to all online staff
                }
            });
    }

    @Command("unban <target>")
    @Permission("smple.command.unban")
    public void unban(
        final CommandSender sender,
        @Argument("target") final String target
    ) {
        final OfflinePlayer offlineTarget = Bukkit.getOfflinePlayerIfCached(target);

        final UUID senderId = sender instanceof Player
            ? ((Player) sender).getUniqueId()
            : PunishmentService.CONSOLE_ID;

        if (offlineTarget == null) {
            sender.sendMessage("Player not found.");
            return;
        }

        this.sdk.getPunishmentService()
            .unban(offlineTarget.getUniqueId(), senderId)
            .thenAccept(success -> {
                if (success) {
                    // Broadcast unban message to server
                } else {
                    sender.sendMessage("Player is not banned.");
                }
            });
    }

}
