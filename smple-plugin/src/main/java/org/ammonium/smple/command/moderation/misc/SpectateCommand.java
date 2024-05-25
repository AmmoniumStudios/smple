package org.ammonium.smple.command.moderation.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SpectateCommand {

    private final Map<UUID, Location> lastLocation;

    public SpectateCommand() {
        this.lastLocation = new HashMap<>();
    }

    @Command("spectate|watch <target>")
    @Permission("smple.moderation.spectate")
    @CommandDescription("Spectate a player")
    public void spectate(final Player player, @Argument("target") final Player target) {
        // Vanish the player
        // Teleport the player to the target

        player.setInvisible(true);
        this.lastLocation.put(player.getUniqueId(), player.getLocation());
        player.teleportAsync(target.getLocation());
    }

    @Command("unspectate|unwatch|us|uw")
    @Permission("smple.moderation.spectate")
    @CommandDescription("Unspectate a player")
    public void unspectate(final Player player) {
        player.teleportAsync(this.lastLocation.get(player.getUniqueId()));
        player.setInvisible(false);
    }

}
