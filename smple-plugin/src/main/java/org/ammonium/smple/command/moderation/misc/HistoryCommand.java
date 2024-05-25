package org.ammonium.smple.command.moderation.misc;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.sdk.SmpleSdk;
import org.ammonium.smple.sdk.plugin.PluginBootstrapper;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;

public class HistoryCommand {

    private final SmpleSdk sdk;

    public HistoryCommand(SmplePlugin bootstrapper) {
        this.sdk = bootstrapper.getSdk();
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

        this.sdk.getPunishmentService()
            .getHistory(offlinePlayer.getUniqueId())
            .thenAccept(punishments -> {
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
