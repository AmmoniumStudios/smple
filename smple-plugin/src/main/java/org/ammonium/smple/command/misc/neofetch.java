package org.ammonium.smple.command.misc;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class neofetch {

    private final SmplePlugin plugin;

    public neofetch(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("neofetch")
    @Permission("smple.misc.neofetch.run")
    @CommandDescription("Run a neofetch command")
    public void neofetch(
        final Player player
    ) {
        player.sendMessage(MiniMessage.miniMessage().deserialize("""
            <blue>        /\\
                   /  \\
                  /\\   \\
                 / > ω <\\
                /   __   \\
               / __|  |__-\\
              /_-''    ''-_\\ </blue>
            """
        ));

        // Run neofetch command
    }
}
