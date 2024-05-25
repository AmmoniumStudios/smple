package org.ammonium.smple.command.moderation;

import java.util.UUID;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.ammonium.smple.sdk.SmpleSdk;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.ammonium.smple.sdk.plugin.PluginBootstrapper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotation.specifier.Greedy;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class KickCommand {

    private final SmpleSdk sdk;

    public KickCommand(PluginBootstrapper bootstrapper) {
        this.sdk = bootstrapper.getSdk();
    }

    @Command("kick <player> <reason>")
    @Permission("smple.moderation.kick")
    @CommandDescription("Kick a player")
    public void kick(
        final CommandSender sender,
        @Argument("player") final Player player,
        @Greedy @Argument("reason") final String reason
    ) {
        final UUID senderId = sender instanceof Player
            ? ((Player) sender).getUniqueId()
            : PunishmentService.CONSOLE_ID;


        this.sdk.getPunishmentService()
            .kick(player.getUniqueId(), senderId, reason)
            .thenAccept(success -> {
                if (success) {
                    sender.sendMessage("Player kicked.");
                    player.kick(MiniMessage.miniMessage().deserialize(reason));
                } else {
                    sender.sendMessage("Failed to kick player.");
                }
            });
    }

}
