package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class CraftCommand {
    
    private final SmplePlugin plugin;
    
    public CraftCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("craft")
    @Permission("smple.workbench.craft")
    @CommandDescription("Open a crafting table")
    public void openCrafting(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openWorkbench(player.getLocation(), true);
        });
    }
}
