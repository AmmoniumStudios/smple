package org.ammonium.smple.sdk.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public final class FancyMessage {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    @Setting
    private final String message;

    public FancyMessage() {
        this.message = "";
    }

    public FancyMessage(String message) {
        this.message = message;
    }

    public void send(Player player, TagResolver... resolvers) {
        player.sendMessage(MINI_MESSAGE.deserialize(message, resolvers));
    }

    public String asJson() {
        return MINI_MESSAGE.serialize(MINI_MESSAGE.deserialize(message));
    }

    public Component asComponent(TagResolver... resolvers) {
        return MINI_MESSAGE.deserialize(message, resolvers);
    }

}
