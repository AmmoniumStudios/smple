package org.ammonium.smple.command.warps.home;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.helpers.WarpHelper;
import org.ammonium.smple.sdk.api.service.impl.HomeService;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class HomeCommand {

    private SmplePlugin plugin;

    public HomeCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("home <name>")
    @Permission("smple.warps.home")
    @CommandDescription("Teleport to a home")
    public void home(
        final Player player,
        @Argument("name") final String name
    ) {
        this.plugin.getSmpleSdk().getHomeService()
            .getByName(player.getUniqueId(), name)
            .thenAccept(home -> {
                if (home == null) player.sendMessage("Home %s does not exist".formatted(name));
                else WarpHelper.teleport(this.plugin, player, home.toLocation());
            });
    }
}
