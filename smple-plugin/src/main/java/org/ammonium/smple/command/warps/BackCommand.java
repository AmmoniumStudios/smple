package org.ammonium.smple.command.warps;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.WarpHelper;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class BackCommand {
    
    private final SmplePlugin plugin;
    
    public BackCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("back")
    @Permission("smple.warps.back")
    @CommandDescription("Teleport back to your previous location")
    public void back(
        final Player player
    ) {
        player.sendMessage("This command is not yet implemented");
        
        // get a players previous locations
        // pop the last location
        
        WarpHelper.teleport(this.plugin, player,
            player.getLocation() // TODO: change this to location popped from stack
        );
    }
}
