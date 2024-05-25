package org.ammonium.smple.command.warps.tp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class TPAcceptCommand {
    
    private SmplePlugin plugin;
    
    public TPAcceptCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("tpaccept")
    @Permission("smple.warps.tp.accept")
    @CommandDescription("Accept a teleport request")
    public void tpAccept(
        final Player player
    ) {
        // check if player has a pending teleport request
        // if player has a pending teleport request
        // if tp request is still valid
        // teleport player to location
        // or teleport player to player who sent request
    }
}
