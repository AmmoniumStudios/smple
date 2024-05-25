package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.InventoryView;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class ETableCommand implements Listener {
    
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
    
    @EventHandler
    public void onPreEnchantTest(PrepareItemEnchantEvent e){
        InventoryView view = e.getView();
        view.setProperty(InventoryView.Property.ENCHANT_BUTTON1, 1);
        view.setProperty(InventoryView.Property.ENCHANT_BUTTON2, 15);
        view.setProperty(InventoryView.Property.ENCHANT_BUTTON3, 30);
        
    }
}
