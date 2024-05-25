package org.ammonium.smple.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.ammonium.smple.sdk.SmpleSdk;
import org.ammonium.smple.sdk.plugin.PluginBootstrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PunishmentListener implements Listener {

    private final SmpleSdk sdk;

    public PunishmentListener(PluginBootstrapper bootstrapper) {
        this.sdk = bootstrapper.getSdk();
    }

    @EventHandler
    public void onPlayerJoin(final AsyncPlayerPreLoginEvent event) {
        this.sdk.getPunishmentService().isBanned(event.getUniqueId()).thenAccept(ban -> {
            if (ban != null) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, "You are banned from the server.");
            }
        });
    }

    @EventHandler
    public void onChat(final AsyncChatEvent event) {
        this.sdk.getPunishmentService().isMuted(event.getPlayer().getUniqueId()).thenAccept(mute -> {
            if (mute != null) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You are muted.");
            }
        });
    }

}
