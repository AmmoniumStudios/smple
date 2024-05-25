package org.ammonium.smple.command.moderation;

import java.util.UUID;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotation.specifier.Greedy;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;

public class KickCommand {

    private final PunishmentService punishmentService;

    public KickCommand(PunishmentService punishmentService) {
        this.punishmentService = punishmentService;
    }

    @Command("kick <player> <reason>")
    public void kick(
        final CommandSender sender,
        @Argument("player") final Player player,
        @Greedy @Argument("reason") final String reason
    ) {
        final UUID senderId = sender instanceof Player
            ? ((Player) sender).getUniqueId()
            : PunishmentService.CONSOLE_ID;


        punishmentService.kick(player.getUniqueId(), senderId, reason).thenAccept(success -> {
            if (success) {
                sender.sendMessage("Player kicked.");
                player.kick(MiniMessage.miniMessage().deserialize(reason));
            } else {
                sender.sendMessage("Failed to kick player.");
            }
        });
    }

}
