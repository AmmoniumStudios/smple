package org.ammonium.smple.command.warps.tp;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.WarpHelper;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class TPCommand {

    private SmplePlugin plugin;

    public TPCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("tp <player>")
    @Permission("smple.warps.tp.run")
    @CommandDescription("Teleport to a player")
    public void tp(
        final Player player,
        @Argument("player") final Player target
    ) {
        // get location of target
        // send teleport request to target

        if (player == target) {
            player.sendMessage("You cannot teleport to yourself");
            return;
        }

        if (player.hasPermission("smple.warps.noaccept")) {

            Location location = target.getLocation();
            WarpHelper.teleport(this.plugin, player, location);
            player.sendMessage("Teleported to " + target.getName());
            return;

        } else {

            // send teleport request to target
            // await response

            player.sendMessage("Teleport request sent to " + target.getName());
            target.sendMessage(player.getName() + " has requested to teleport to you. Type /tpaccept to accept");
        }
    }
}
