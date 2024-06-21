package org.ammonium.smple.command.warps.home;

import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.config.Config;
import org.ammonium.smple.sdk.api.model.Home;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

import java.util.List;

public class SetHomeCommand {

    private SmplePlugin plugin;

    public SetHomeCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("sethome <name>")
    @Permission("smple.warps.home")
    @CommandDescription("Set a home")
    public void setHome(
        final Player player,
        @Argument("name") final String name
    ) {
        final int maxHomes = Config.Warps.get().getMaxHomes();
        final List<Home> currentHomes = this.plugin.getSmpleSdk().getHomeService()
            .getAll(player.getUniqueId())
            .join(); // blocking here should be fine? if not, refactor to async
        
        final Location location = player.getLocation();

        if (currentHomes.size() < maxHomes || player.hasPermission("smple.warps.home.unlimited")) {
            if (currentHomes.stream().noneMatch(home -> home.name().equals(name))) {
                Home home = new Home(
                    player.getUniqueId(),
                    name,
                    location.getWorld().getName(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ()
                );

                this.plugin.getSmpleSdk().getHomeService().create(home);
                player.sendMessage("Home %s set".formatted(name));
            } else {
                player.sendMessage("Home %s already exists, please delete before setting".formatted(name));
            }
        } else {
            player.sendMessage("You have reached the maximum amount of homes");
        }
    }
}
