package org.ammonium.smple;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.ammonium.smple.command.ExampleCommand;
import org.ammonium.smple.sdk.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmplePlugin extends JavaPlugin {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        CommandManager.create(this)
            .withCommands(
                new ExampleCommand()
            );

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.broadcast(
                Component.text("Hello!")
                    .decorate(TextDecoration.BOLD, TextDecoration.ITALIC)
                    .color(NamedTextColor.AQUA)
                    .clickEvent(ClickEvent.clickEvent(
                        ClickEvent.Action.RUN_COMMAND, "/example")),
                "smple.example");

        }, 20, 20);
    }

    @Override
    public void onDisable() {

    }
}
