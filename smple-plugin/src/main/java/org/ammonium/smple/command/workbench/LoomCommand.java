package org.ammonium.smple.command.workbench;
import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class LoomCommand {
    
    private final SmplePlugin plugin;
    
    public LoomCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("loom")
    @Permission("smple.workbench.loom")
    @CommandDescription("Open a loom")
    public void openLoom(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openLoom(player.getLocation(), true);
        });
    }
    
}
