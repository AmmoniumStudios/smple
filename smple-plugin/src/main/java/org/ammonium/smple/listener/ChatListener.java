package org.ammonium.smple.listener;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.ammonium.smple.config.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public final class ChatListener implements Listener, ChatRenderer {

    @EventHandler
    public void onChat(final AsyncChatEvent event) {
        event.renderer(this);
    }

    @Override
    public @NotNull Component render(
        @NotNull Player player,
        @NotNull Component sourceDisplayName,
        @NotNull Component message,
        @NotNull Audience audience
    ) {
        return Config.get().getChatFormat().asComponent(
            Placeholder.component("display_name", sourceDisplayName),
            Placeholder.component("message", message)
        );
    }
}
