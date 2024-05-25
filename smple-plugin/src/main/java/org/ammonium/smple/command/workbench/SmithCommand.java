package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SmithCommand {

    private final SmplePlugin plugin;

    public SmithCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("smith")
    @Permission("smple.workbench.smith")
    @CommandDescription("Open a smithing table")
    public void openSmithingTable(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openSmithingTable(player.getLocation(), true);
        });
    }
}
