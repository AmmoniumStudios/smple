package org.ammonium.smple.command.moderation.misc;

import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;

public class HistoryCommand {

    private final PunishmentService punishmentService;

    public HistoryCommand(PunishmentService punishmentService) {
        this.punishmentService = punishmentService;
    }

    @Command("history|hist|punishments <player> ")
    public void history(
        final CommandSender sender,
        @Argument("player") final String player
    ) {
        // Implementation

        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayerIfCached(player);
        if (offlinePlayer == null) {
            // Player not found
            return;
        }

        this.punishmentService.getHistory(offlinePlayer.getUniqueId()).thenAccept(punishments -> {
            // Build message
        });
    }

    @Command("history|hist|punishments remove <player> <id> ")
    public void removePunishment(
        final CommandSender sender,
        @Argument("player") final String player,
        @Argument("id") final int id
    ) {
        // Implementation

        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayerIfCached(player);
        if (offlinePlayer == null) {
            // Player not found
            return;
        }

        // Remove punishment id
    }

}
