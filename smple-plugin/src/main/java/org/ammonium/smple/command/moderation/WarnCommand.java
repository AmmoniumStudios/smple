package org.ammonium.smple.command.moderation;

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

public class WarnCommand {

    private final PunishmentService punishmentService;

    public WarnCommand(PunishmentService punishmentService) {
        this.punishmentService = punishmentService;
    }

    @Command("warn <target> <reason>")
    @Permission("smple.moderation.warn")
    @CommandDescription("Warn a player")
    public void warn(
        final CommandSender sender,
        @Argument("target") final String target,
        @Argument("reason") @Greedy final String reason
    ) {
        final UUID senderId = sender instanceof Player
            ? ((Player) sender).getUniqueId()
            : PunishmentService.CONSOLE_ID;

        final OfflinePlayer offlinePlayer = sender.getServer().getOfflinePlayerIfCached(target);
        if (offlinePlayer == null) {
            sender.sendMessage("Player not found.");
            return;
        }

        punishmentService.warn(
            offlinePlayer.getUniqueId(),
            senderId,
            reason
        ).thenAccept(success -> {
            if (success) {
                sender.sendMessage("Player warned.");
            } else {
                sender.sendMessage("Failed to warn player.");
            }
        });
    }

}
