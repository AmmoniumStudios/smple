package org.ammonium.smple.command.warps.spawn;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.WarpHelper;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SpawnCommand {

    private SmplePlugin plugin;

    public SpawnCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("spawn")
    @Permission("smple.warps.spawn.run")
    @CommandDescription("Teleport to spawn")
    public void spawn(
        final Player player
    ) {

        World world = player.getWorld();
        
        WarpHelper.teleport(this.plugin, player,
            world.getSpawnLocation()
        );
    }
}
