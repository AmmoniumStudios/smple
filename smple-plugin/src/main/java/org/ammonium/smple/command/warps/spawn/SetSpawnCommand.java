package org.ammonium.smple.command.warps.spawn;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SetSpawnCommand {
    
    private SmplePlugin plugin;
    
    public SetSpawnCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("setspawn")
    @Permission("smple.warps.spawn.set")
    @CommandDescription("Set the spawn location")
    public void setSpawn(
        final Player player
    ) {
        final Location location = player.getLocation();

        // set world spawn
        World world = location.getWorld();
        if (world != null) world.setSpawnLocation(
            location.getBlockX(),
            location.getBlockY(),
            location.getBlockZ()
        );
        
        // TODO: set spawn with SDK
        
        player.sendMessage("Spawn set");
    }
}
