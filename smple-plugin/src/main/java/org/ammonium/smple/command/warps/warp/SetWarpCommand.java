package org.ammonium.smple.command.warps.warp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SetWarpCommand {

    private SmplePlugin plugin;

    public SetWarpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("setwarp <name>")
    @Permission("smple.warps.warp.manage")
    @CommandDescription("Set a warp")
    public void setWarp(
        final Player player,
        @Argument("name") final String name
    ) {
        // set warp location
    }
}
