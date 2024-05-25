package org.ammonium.smple.command.misc;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class HelpCommand {

    private final SmplePlugin plugin;

    public HelpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("smple_help [command]")
    @Permission("smple.misc.sudo.run")
    @CommandDescription("Run a command as console")
    public void sudo(
        final Player player,
        @Argument("command") final String command
    ) {
        // TODO: Implement this method
    }


}

// TODO: Fully implement the HelpCommand class once all the other classes are implemented