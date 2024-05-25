package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class GrindCommand {

    private final SmplePlugin plugin;

    public GrindCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("grind")
    @Permission("smple.workbench.grind")
    @CommandDescription("Open a grindstone")
    public void openGrindstone(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openGrindstone(player.getLocation(), true);
        });
    }
}
