package org.ammonium.smple.command.moderation.misc;

import org.bukkit.command.CommandSender;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class RulesCommand {

    @Command("rules")
    @Permission("smple.moderation.rules")
    @CommandDescription("Display the server rules")
    public void rules(final CommandSender sender) {
        // Display the server rules
    }

    @Command("rule <rule>")
    @Permission("smple.moderation.rules")
    @CommandDescription("Display a specific rule")
    public void rules(final CommandSender sender, @Argument("rule") String rule) {
        // Display the specified rule
    }

}
