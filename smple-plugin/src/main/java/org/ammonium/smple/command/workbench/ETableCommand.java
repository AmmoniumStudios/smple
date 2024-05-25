package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class ETableCommand {
    
    private final SmplePlugin plugin;
    
    public ETableCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("etable")
    @Permission("smple.workbench.etable")
    @CommandDescription("Open an etable")
    public void openETable(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            // TODO: check what level this is, it needs to be a max level etable
            player.openEnchanting(player.getLocation(), true);
        });
    }
}
