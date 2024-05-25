package org.ammonium.smple.sdk.plugin;

import org.ammonium.smple.sdk.SmpleSdk;
import org.ammonium.smple.sdk.config.ConfigManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PluginBootstrapper extends JavaPlugin {

    private SmpleSdk smpleSdk;
    private ConfigManager configManager;

    @Override
    public void onLoad() {
        this.smpleSdk = SmpleSdk.get();
        this.configManager = new ConfigManager(this);

        load();
    }

    @Override
    public void onEnable() {
        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    public abstract void load();

    public abstract void enable();

    public abstract void disable();

    protected void setupListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public SmpleSdk getSdk() {
        return smpleSdk;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
