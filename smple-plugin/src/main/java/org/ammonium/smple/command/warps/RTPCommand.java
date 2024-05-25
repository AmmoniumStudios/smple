package org.ammonium.smple.command.warps;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.WarpHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class RTPCommand {

    private final SmplePlugin plugin;

    public RTPCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("rtp")
    @Permission("smple.warps.rtp")
    @CommandDescription("Teleport to a random location")
    public void rtp(
        final Player player
    ) {

        // if player is on cooldown and they dont have the permission "smple.warps.nocooldown"
        // return "You are on cooldown for x seconds"

        // if player is in combat and they dont have the permission "smple.warps.nocombat"
        // return "You are in combat"


        final int radius = plugin.getConfig().getInt("rtp.radius");
        final World world = Bukkit.getWorld("world");

        if (world == null) {
            player.sendMessage("Where tf are you?");
            return;
        }

        final Location spawn = world.getSpawnLocation();
        boolean safe = false;


        while (!safe) {
            final int x = spawn.getBlockX() + (int) (Math.random() * radius * 2 - radius);
            final int z = spawn.getBlockZ() + (int) (Math.random() * radius * 2 - radius);

            Location loc = new Location(world, x, world.getHighestBlockYAt(x, z), z);

            if (loc.getBlock().isPassable()) {

                // if player has permission "smple.warps.rtp.nodelay"
                // teleport player instantly
                // if not, teleport player after time specified in config

                WarpHelper.teleport(this.plugin, player, loc);
                safe = true;
            }
        }

    }
}
