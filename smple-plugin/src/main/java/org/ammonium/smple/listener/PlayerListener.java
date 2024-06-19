package org.ammonium.smple.listener;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.sdk.api.model.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public final class PlayerListener implements Listener {

    private final SmplePlugin plugin;

    public PlayerListener(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        final UUID uniqueId = event.getUniqueId();
        final String username = event.getName();

        this.plugin.getSdk().getUserService().loadUser(uniqueId, username);
    }

}
