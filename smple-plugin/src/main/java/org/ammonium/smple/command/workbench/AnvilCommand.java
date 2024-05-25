package org.ammonium.smple.command.workbench;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
