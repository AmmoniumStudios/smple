package org.ammonium.smple.command.warps.home;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class DelHomeCommand {

    final SmplePlugin plugin;

    public DelHomeCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("delhome <home>")
    @Permission("smple.warps.home")
    @CommandDescription("Delete a home")
    public void delHome(
        final Player player,
        @Argument("home") final String home
    ) {
        this.plugin.getSmpleSdk().getHomeService().getByName(player.getUniqueId(), home)
            .thenAccept(h -> {
                if (h == null) {
                    player.sendMessage("Home %s does not exist".formatted(home));
                    return;
                } else {
                    this.plugin.getSmpleSdk().getHomeService().delete(player.getUniqueId(), h);
                    player.sendMessage("Home %s deleted".formatted(home));
                }
            });
    }
}
