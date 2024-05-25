package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class EChestComand {

    private final SmplePlugin plugin;

    public EChestComand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("echest")
    @Permission("smple.workbench.echest")
    @CommandDescription("Open an echest")
    public void openEChest(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openInventory(player.getEnderChest());
        });
    }
}
