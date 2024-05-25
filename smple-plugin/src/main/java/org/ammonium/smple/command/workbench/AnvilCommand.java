package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class AnvilCommand {
    
    private final SmplePlugin plugin;
    
    public AnvilCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("anvil")
    @Permission("smple.workbench.anvil")
    @CommandDescription("Open an anvil")
    public void openAnvil(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openAnvil(player.getLocation(), true);
        });
    }
    
}
