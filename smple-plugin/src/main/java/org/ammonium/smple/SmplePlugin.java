package org.ammonium.smple;

import org.ammonium.smple.command.ExampleCommand;
import org.ammonium.smple.sdk.command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmplePlugin extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        CommandManager.create(this)
            .withCommands(
                new ExampleCommand()
            );
    }

    @Override
    public void onDisable() {

    }
}
