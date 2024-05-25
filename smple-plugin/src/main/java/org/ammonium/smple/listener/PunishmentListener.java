package org.ammonium.smple.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PunishmentListener implements Listener {

    private final PunishmentService punishmentManager;

    public PunishmentListener(PunishmentService punishmentManager) {
        this.punishmentManager = punishmentManager;
    }

    @EventHandler
    public void onPlayerJoin(final AsyncPlayerPreLoginEvent event) {
        punishmentManager.isBanned(event.getUniqueId()).thenAccept(ban -> {
            if (ban != null) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, "You are banned from the server.");
            }
        });
    }

    @EventHandler
    public void onChat(final AsyncChatEvent event) {
        punishmentManager.isMuted(event.getPlayer().getUniqueId()).thenAccept(mute -> {
            if (mute != null) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You are muted.");
            }
        });
    }

}
