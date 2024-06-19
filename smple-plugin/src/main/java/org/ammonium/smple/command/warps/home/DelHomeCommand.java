package org.ammonium.smple.command.warps.home;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class DelHomeCommand {

    final SmplePlugin plugin;

    public DelHomeCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("delhome <home>")
    @Permission("smple.warps.home")
    @CommandDescription("Delete a home")
    public void delHome(
        final Player player,
        @Argument("home") final String home
    ) {
        // get all homes of player
        // if home exists, delete it
        
        player.sendMessage("Home %s deleted".formatted(home));
    }
}
