package org.ammonium.smple.command.warps.warp;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.ammonium.smple.SmplePlugin;
import org.ammonium.smple.config.Messages;
import org.ammonium.smple.sdk.api.model.Warp;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class WarpCommand {

    private SmplePlugin plugin;

    public WarpCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }

    @Command("warp <name>")
    @Permission("smple.warps.warp.run")
    @CommandDescription("Warp to a location")
    public void warp(final Player player, @Argument("name") final String name) {
        Messages messages = Messages.get();
        try {
            Warp warp = this.plugin.getWarpService().get(name);
            if (warp != null) {
                player.teleportAsync(warp.toLocation());
                messages.getWarp().getWarpTeleported().send(player, Placeholder.parsed("warp", name));
            }
        } catch (IllegalArgumentException e) {
            messages.getWarp().getWarpNotFound().send(player, Placeholder.parsed("warp", name));
        }
    }

    @Command("setwarp <name>")
    @Permission("smple.warps.setwarp.run")
    @CommandDescription("Set a warp location")
    public void setWarp(final Player player, @Argument("name") final String name) {
        Messages messages = Messages.get();

        Warp warp = new Warp(
            name,
            player.getWorld().getName(),
            player.getLocation().getBlockX(),
            player.getLocation().getBlockY(),
            player.getLocation().getBlockZ()
        );

        this.plugin.getWarpService().create(warp);
        this.plugin.getWarpService().saveToFile();
        messages.getWarp().getWarpSet().send(player, Placeholder.parsed("warp", name));
    }

    @Command("delwarp <name>")
    @Permission("smple.warps.delwarp.run")
    @CommandDescription("Delete a warp location")
    public void delWarp(final Player player, @Argument("name") final String name) {
        Messages messages = Messages.get();
        try {
            this.plugin.getWarpService().delete(name);
            this.plugin.getWarpService().saveToFile();
            messages.getWarp().getWarpRemoved().send(player, Placeholder.parsed("warp", name));
        } catch (IllegalArgumentException e) {
            messages.getWarp().getWarpNotFound().send(player, Placeholder.parsed("warp", name));
        }
    }

}
