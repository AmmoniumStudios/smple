package org.ammonium.smple.command.nickname;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.NicknameHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class ClearCommand {
    
    private final SmplePlugin plugin;
    
    public ClearCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("nickname clear <target>")
    @Permission("smple.nickname.manage")
    @CommandDescription("Clear a player's nickname")
    public void clearOthersNickname(
        final CommandSender sender,
        @Argument("target")
        final Player target
    ) {
        sender.sendMessage("Cleared %s's nickname".formatted(target.getName()));
        NicknameHelper.removeNickname(target, true);
    }
    
    @Command("nickname clear")
    @Permission("smple.nickname.set")
    @CommandDescription("Clear your own nickname")
    public void clearOwnNickname(
        final Player player
    ) {
        NicknameHelper.removeNickname(player, false);
    }
}
