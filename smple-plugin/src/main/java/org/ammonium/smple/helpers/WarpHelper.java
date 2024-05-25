package org.ammonium.smple.helpers;

import org.ammonium.smple.SmplePlugin;
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
        if (onCooldown && !player.hasPermission("smple.warps.nocooldown"))
        {
            int cooldown = 5; // TODO: get cooldown time from config
            player.sendMessage("You must wait %d seconds before teleporting again".formatted(cooldown));;
            return;
        }
        
        // if user does not have the bypass permission, teleport after delay
        if (!player.hasPermission("smple.warps.nodelay")) {
            int delay = 5; // get delay from config
            
            player.sendMessage("Teleporting in %d seconds".formatted(delay));
            
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.teleportAsync(location);
                
            }, delay * 20);
        } else {
            player.sendMessage("Teleporting");
            player.teleportAsync(location);
        }
    }
    
}