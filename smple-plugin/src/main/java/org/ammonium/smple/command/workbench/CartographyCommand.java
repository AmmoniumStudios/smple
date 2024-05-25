package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class CartographyCommand {

    private final SmplePlugin plugin;

    public CartographyCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("cartography")
    @Permission("smple.workbench.cartography")
    @CommandDescription("Open a cartography table")
    public void openCartography(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openCartographyTable(player.getLocation(), true);
        });
    }

}
