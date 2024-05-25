package org.ammonium.smple.command.moderation.misc;

import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Default;
import org.incendo.cloud.annotations.Permission;

public class PunishCommand {

    @Command("punish|p <target> <violation> [silent]")
    @Permission("smple.moderation.punish.run")
    @CommandDescription("Punish a player for a violation")
    public void punish(
        final Player player,
        @Argument("target") final String target,
        @Argument("violation") int violationId,
        @Argument("silent") @Default("false") boolean silent
    ) {
        // Follow the pre-defined configuration values to punish a player
        // based off the violation identifier.

        // This is a placeholder method, and should be replaced with the
        // actual implementation of the punishment system.
    }
}
