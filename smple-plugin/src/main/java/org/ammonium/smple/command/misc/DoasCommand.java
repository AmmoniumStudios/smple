package org.ammonium.smple.command.misc;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotation.specifier.Greedy;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class DoasCommand {
    
    private final SmplePlugin plugin;
    
    public DoasCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("doas <target> <args>")
    @Permission("smple.misc.doas.run")
    @CommandDescription("Run a command as console")
    public void doas(
        final CommandSender sender,
        @Argument("target")
        final Player target,
        @Greedy @Argument("args")
        final String args
    ) {
        if (target.isOnline()) {
            Bukkit.getScheduler().runTask(
                plugin, () -> {
                    Bukkit.dispatchCommand(target, args);
                }
            );
        } else {
            sender.sendMessage("Player %s is not online".formatted(target.getName()));
        }
    }
    
    
}
