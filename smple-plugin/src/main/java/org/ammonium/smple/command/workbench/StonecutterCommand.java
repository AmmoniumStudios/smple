package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class StonecutterCommand {

    private final SmplePlugin plugin;

    public StonecutterCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("stonecutter")
    @Permission("smple.workbench.stonecutter")
    @CommandDescription("Open a stonecutter")
    public void openStonecutter(
        final Player player
    ) {
        Bukkit.getScheduler().runTask(plugin, () ->
            player.openStonecutter(player.getLocation(), true)
        );
    }
}
