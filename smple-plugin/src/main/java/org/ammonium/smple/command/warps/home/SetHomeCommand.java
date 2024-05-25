package org.ammonium.smple.command.warps.home;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SetHomeCommand {

    private SmplePlugin plugin;

    public SetHomeCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("sethome <name>")
    @Permission("smple.warps.home")
    @CommandDescription("Set a home")
    public void setHome(
        final Player player,
        @Argument("name") final String name
    ) {
        final Location location = player.getLocation();

        // set home

        player.sendMessage("Home \"%s\" set".formatted(name));
    }
}
