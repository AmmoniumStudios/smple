package org.ammonium.smple.command;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;

public class CmdLogsCommand {

    private final SmplePlugin plugin;

    public CmdLogsCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("cmdlogs <player>")
    public void viewLogs(final CommandSender sender, @Argument("player") final Player player) {
        final String command = this.plugin.commandLogs.get(player.getUniqueId());

        if (command == null) {
            sender.sendMessage("No command executed by " + player.getName());
            return;
        }

        sender.sendMessage("Command executed by " + player.getName() + ": " + command);
    }
}
