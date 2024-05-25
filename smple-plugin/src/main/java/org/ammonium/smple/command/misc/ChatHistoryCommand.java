package org.ammonium.smple.command.misc;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class ChatHistoryCommand {

    final SmplePlugin plugin;

    public ChatHistoryCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("chathistory <player>")
    @Permission("smple.misc.chathistory.view")
    @CommandDescription("View the chat history of a player")
    public void chathistory(
        final Player player,
        @Argument("player") final String target
    ) {
        player.sendMessage("This command is not implemented yet");

        // Check through chat history

        // if target in chat history

        // return chat history of target

        // else

        // return "Player with username %s not found".formatted(target);

    }
}
