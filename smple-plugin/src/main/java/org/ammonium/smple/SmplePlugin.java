package org.ammonium.smple;

import org.ammonium.smple.config.Config;
import org.ammonium.smple.listener.PunishmentListener;
import org.ammonium.smple.sdk.command.CommandManager;
import org.ammonium.smple.sdk.config.ConfigManager;
import org.ammonium.smple.sdk.plugin.PluginBootstrapper;

public final class SmplePlugin extends PluginBootstrapper {

    @Override
    public void load() {
        ConfigManager.getInstance().initConfigs(
            Config.class
        );
    }

    @Override
    public void enable() {
        setupListeners(
            new PunishmentListener(this)
        );

        CommandManager.create(this)
            .withCommands("org.ammonium.smple.command", this, getSdk());
    }

    @Override
    public void disable() {

    }

}
