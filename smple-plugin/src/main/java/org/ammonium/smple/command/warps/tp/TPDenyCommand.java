package org.ammonium.smple.command.warps.tp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class TPDenyCommand {
    
    private SmplePlugin plugin;
    
    public TPDenyCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("tpdeny")
    @Permission("smple.warps.tp.deny")
    @CommandDescription("Deny a teleport request")
    public void tpDeny(
        final Player player
    ) {
        // check if player has a pending teleport request
        // if player has a pending teleport request
        // if tp request is still valid
        // deny teleport request
    }
}
