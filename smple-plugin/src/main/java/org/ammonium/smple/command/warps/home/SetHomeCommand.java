package org.ammonium.smple.command.warps.home;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

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
        final int maxHomes = 3; // TODO: get max homes from config
        final int currentHomes = 0; // TODO: get current homes of player
        
        final Location location = player.getLocation();

        if (currentHomes < maxHomes || player.hasPermission("smple.warps.home.unlimited")) {
            
            // check if home with name already exists
            // if it does, cancel and send message to player (home already exists)
            // else, create a new home
            
            player.sendMessage("Home \"%s\" set".formatted(name));
        } else {
            player.sendMessage("You have reached the maximum amount of homes");
        }
    }
}
