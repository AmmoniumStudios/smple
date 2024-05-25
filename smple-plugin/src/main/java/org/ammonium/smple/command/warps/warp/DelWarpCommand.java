package org.ammonium.smple.command.warps.warp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class DelWarpCommand {
    
    private SmplePlugin plugin;
    
    public DelWarpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("delwarp <name>")
    @Permission("smple.warps.warp.manage")
    @CommandDescription("Delete a warp")
    public void delWarp(
        final Player player,
        @Argument("name")
        final String name
    ) {
        // check if warp exists
        // if warp exists, delete warp
    }
}
