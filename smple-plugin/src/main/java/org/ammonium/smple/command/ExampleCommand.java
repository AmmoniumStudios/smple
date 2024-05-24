package org.ammonium.smple.command;

import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class ExampleCommand {

    @Command(value = "example")
    @Permission("smple.example")
    @CommandDescription("An example command")
    public void example(final Player player) {
        player.sendMessage("Hello, world!");
    }

}
