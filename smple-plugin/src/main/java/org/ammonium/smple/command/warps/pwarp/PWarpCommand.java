package org.ammonium.smple.command.warps.pwarp;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.WarpHelper;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class PWarpCommand {

    private SmplePlugin plugin;

    public PWarpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("pwarp <name>")
    @Permission("smple.warps.pwarp.run")
    @CommandDescription("Teleport to a pwarp")
    public void pwarp(
        final Player player,
        @Argument("name") final String name
    ) {
        // get pwarp location
        // teleport player to location

        WarpHelper.teleport(this.plugin, player,
            player.getLocation() // TODO: change this to location of pwarp
        );
    }
}
