package org.ammonium.smple.helpers;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WarpHelper {


    public static void teleport(
        final SmplePlugin plugin,
        final Player player,
        final Location location
    ) {
        boolean inCombat = false; // check if user is in combat

        // if in combat, or has the bypass permission, cancel teleport
        if (inCombat && !player.hasPermission("smple.warps.nocombat")) {
            player.sendMessage("You cannot teleport while in combat");
            return;
        }

        boolean onCooldown = false; // check if user is on cooldown

        // if on cooldown, or has the bypass permission, cancel teleport
        if (onCooldown && !player.hasPermission("smple.warps.nocooldown")) {
            int cooldown = 5; // TODO: get cooldown time from config
            player.sendMessage("You must wait %d seconds before teleporting again".formatted(cooldown));
            return;
        }

        // if user does not have the bypass permission, teleport after delay
        if (!player.hasPermission("smple.warps.nodelay")) {
    
            final int delay = Config.Warps.get().getWarpDelay();

            player.sendMessage("Teleporting in %d seconds".formatted(delay));
            
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.sendMessage("Teleporting");
                
                Location currentLocation = player.getLocation();
                
                Location target = new Location(
                    location.getWorld(),
                    location.getX(),
                    location.getY(),
                    location.getZ(),
                    currentLocation.getYaw(),
                    currentLocation.getPitch()
                );
                
                player.teleportAsync(target);
            }, delay * 20L);
        } else {
            player.sendMessage("Teleporting");
            
            Location currentLocation = player.getLocation();
            
            Location target = new Location(
                location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ(),
                currentLocation.getYaw(),
                currentLocation.getPitch()
            );
            
            player.teleportAsync(target);
        }
    }

}
