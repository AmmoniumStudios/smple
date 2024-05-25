package org.ammonium.smple.command.warps.pwarp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class DelPWarpCommand {
    
    private SmplePlugin plugin;
    
    public DelPWarpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("pwarp del <name>")
    @Permission("smple.warps.pwarp.manage")
    @CommandDescription("Delete a pwarp")
    public void delPWarp(
        final Player player,
        final String name
    ) {
        // check if pwarp exists
        // if pwarp exists, check if player is owner
        // if player is owner, delete pwarp
        // if player is not owner, check if player has permission
        // if player has permission, delete pwarp
        // else, send message saying they don't have permission
    }
}
