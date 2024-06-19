package org.ammonium.smple.command.misc;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.sdk.SmpleSdk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class CmdHistoryCommand {

    private final SmpleSdk sdk;

    public CmdHistoryCommand(SmplePlugin plugin) {
        this.sdk = plugin.getSdk();
    }

    @Command("cmdlogs <player>")
    @Permission("smple.cmdlogs")
    @CommandDescription("View the command history of a player")
    public void viewLogs(final CommandSender sender, @Argument("player") final Player player) {

    }
}
