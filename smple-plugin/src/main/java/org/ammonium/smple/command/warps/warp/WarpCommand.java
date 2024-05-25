package org.ammonium.smple.command.warps.warp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class WarpCommand {

    private SmplePlugin plugin;

    public WarpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("warp <name>")
    @Permission("smple.warps.warp.run")
    @CommandDescription("Warp to a location")
    public void warp(
        final Player player,
        @Argument("name") final String name
    ) {
        // check if warp exists
        // if warp exists, teleport player to warp location
    }
}
