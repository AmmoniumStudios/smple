package org.ammonium.smple.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.ammonium.smple.SmplePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements Listener {

    private final SmplePlugin plugin;


    public PlayerListener(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onCommandExecute(final PlayerCommandPreprocessEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        final String command = event.getMessage();

        this.plugin.commandLogs.put(uuid, command);
    }


}
