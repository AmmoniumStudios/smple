package org.ammonium.smple.command.nickname;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.NicknameHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotation.specifier.Greedy;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SetCommand {

    private final SmplePlugin plugin;

    public SetCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("nickname set <target> <nickname>")
    @Permission("smple.nickname.manage")
    @CommandDescription("Set a player's nickname")
    public void setOthersNickname(
        final CommandSender sender,
        @Argument("target") final Player target,
        @Greedy @Argument("nickname") final String nickname
    ) {
        sender.sendMessage("Changed %s's nickname to %s".formatted(target.getName(), nickname));
        NicknameHelper.setNickname(target, nickname, true);
    }

    @Command("nickname <nickname>")
    @Permission("smple.nickname.set")
    @CommandDescription("Set your own nickname")
    public void setOwnNicknameShort(
        final Player player,
        @Greedy @Argument("nickname") final String nickname
    ) {
        NicknameHelper.setNickname(player, nickname, false);
    }

}
