package org.ammonium.smple.command.warps.home;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.WarpHelper;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class HomeCommand {

    private SmplePlugin plugin;

    public HomeCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("home <name>")
    @Permission("smple.warps.home")
    @CommandDescription("Teleport to a home")
    public void home(
        final Player player,
        @Argument("name") final String name
    ) {
        // get homes of player
        // if home exists, get location

        WarpHelper.teleport(this.plugin, player,
            player.getLocation() // TODO: change this to location of home
        );
    }
}
