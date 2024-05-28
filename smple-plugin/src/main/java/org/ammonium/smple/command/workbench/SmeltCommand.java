package org.ammonium.smple.command.workbench;

import java.util.Iterator;
import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

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
        final ItemStack item = player.getInventory().getItemInMainHand();
        ItemStack result;

        for (Iterator<Recipe> it = Bukkit.recipeIterator(); it.hasNext(); ) {
            Recipe recipe = it.next();
            if (recipe instanceof FurnaceRecipe furnaceRecipe) {
                if (furnaceRecipe.getInputChoice().test(item)) {
                    result = furnaceRecipe.getResult();
                    result.setAmount(item.getAmount());

                    player.getInventory().setItemInMainHand(result);
                    break;
                }
            }
        }
    }
}
