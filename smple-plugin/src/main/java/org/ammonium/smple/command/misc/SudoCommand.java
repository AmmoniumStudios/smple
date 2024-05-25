package org.ammonium.smple.command.misc;

import org.ammonium.smple.SmplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotation.specifier.Greedy;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class SudoCommand {
    
    private final SmplePlugin plugin;
    
    public SudoCommand(SmplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Command("sudo <args>")
    @Permission("smple.misc.sudo.run")
    @CommandDescription("Run a command as console")
    public void sudo(
            final Player player,
            @Greedy @Argument("args")
            final String args
    ) {
        Bukkit.getScheduler().runTask(
            plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), args);
            }
        );
    }


}
