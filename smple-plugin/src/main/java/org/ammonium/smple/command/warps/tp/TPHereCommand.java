package org.ammonium.smple.command.warps.tp;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class TPHereCommand {

    private SmplePlugin plugin;

    public TPHereCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("tphere <player>")
    @Permission("smple.warps.tp.here")
    @CommandDescription("Teleport a player to you")
    public void tpHere(
        final Player player,
        @Argument("player") final Player target
    ) {
        // teleport target to player
    }
}
