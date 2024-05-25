package org.ammonium.smple.command.warps.pwarp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SetPWarpCommand {
    
    private SmplePlugin plugin;
    
    public SetPWarpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("pwarp set <name>")
    @Permission("smple.warps.pwarp.manage")
    @CommandDescription("Set a pwarp")
    public void setPWarp(
        final Player player,
        @Argument("name")
        final String name
    ) {
        // check if pwarp exists
        // if pwarp exists, check if player is owner
        // if player is owner, update pwarp
        // if player is not owner, check if player has permission
        // if player has permission, update pwarp
        // else, send message saying they don't have permission
    }
}
