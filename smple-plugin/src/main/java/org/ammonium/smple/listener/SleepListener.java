package org.ammonium.smple.listener;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.config.Sleep;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepListener implements Listener {

    private final SmplePlugin plugin;
    
    public SleepListener(SmplePlugin plugin) { this.plugin = plugin; }
    
    @EventHandler
    public void onPlayerSleep(final PlayerBedEnterEvent event) {
        
        final Sleep sleepConfig = Sleep.get();
        
        final int minPlayersSleeping = sleepConfig.getMinPlayersSleeping();
        final int nightSkipDelay = sleepConfig.getNightSkipDelay();
        
        // for each player, check if they are sleeping
        int playersSleeping = 0;
        
        for (final Player player : event.getPlayer().getWorld().getPlayers()) {
            if (player.isSleeping()) {
                playersSleeping++;
            }
        }
        
        if (playersSleeping >= minPlayersSleeping) {
                
            event.getPlayer().getWorld().getPlayers().forEach(player -> {
                player.sendMessage("Enough players are sleeping, Skipping night!");
            });
            
            Bukkit.getServer().getScheduler().runTaskLater(this.plugin,
                () -> { event.getPlayer().getWorld().setTime(0); },
                nightSkipDelay * 20L
            );
        }
    }
}
