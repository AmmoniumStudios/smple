package org.ammonium.smple.command.workbench;
import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

import java.util.Iterator;

public class SmeltCommand {
    
    private final SmplePlugin plugin;
    
    public SmeltCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("smelt")
    @Permission("smple.workbench.smith")
    @CommandDescription("Open a smithing table")
    public void smeltHand(
        final Player player
    ) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemStack result = null;
        Iterator<Recipe> iter = Bukkit.recipeIterator();
        while (iter.hasNext()) {
            Recipe recipe = iter.next();
            if (!(recipe instanceof FurnaceRecipe)) continue;
            if (((FurnaceRecipe) recipe).getInput().getType() != item.getType()) continue;
            result = recipe.getResult();
            break;
        }
        result.setAmount(item.getAmount());
        
    }
}
