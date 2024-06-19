package org.ammonium.smple.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.ammonium.smple.config.Messages;
import org.ammonium.smple.sdk.SmpleSdk;
import org.ammonium.smple.sdk.config.serializer.DurationSerializer;
import org.ammonium.smple.sdk.plugin.PluginBootstrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PunishmentListener implements Listener {

    private final SmpleSdk sdk;

    public PunishmentListener(PluginBootstrapper bootstrapper) {
        this.sdk = bootstrapper.getSdk();
    }

    @EventHandler
    public void onPlayerJoin(final AsyncPlayerPreLoginEvent event) {
        final Messages messages = Messages.get();
        System.out.println(event.getUniqueId());
        this.sdk.getPunishmentService().isBanned(event.getUniqueId()).thenAccept(ban -> {
            if (ban != null) {
                event.disallow(
                    AsyncPlayerPreLoginEvent.Result.KICK_BANNED,
                    messages.getBanMessage().asComponent(
                        Placeholder.parsed("reason", ban.reason())
                    )
                );
            }
        });
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(final AsyncChatEvent event) {
        final Messages messages = Messages.get();
        this.sdk.getPunishmentService().isMuted(event.getPlayer().getUniqueId()).thenAccept(mute -> {
            if (mute != null) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(
                    messages.getMuted().asComponent(
                        Placeholder.parsed("reason", mute.reason()),
                        Placeholder.parsed("duration",
                            DurationSerializer.get().serializeToString(mute.duration())
                        )
                    )
                );
            }
        });
    }

}
