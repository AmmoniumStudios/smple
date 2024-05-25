package org.ammonium.smple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.ammonium.smple.command.ExampleCommand;
import org.ammonium.smple.command.misc.CmdHistoryCommand;
import org.ammonium.smple.command.misc.DoasCommand;
import org.ammonium.smple.command.misc.SudoCommand;
import org.ammonium.smple.command.misc.neofetch;
import org.ammonium.smple.command.moderation.BanCommand;
import org.ammonium.smple.command.moderation.KickCommand;
import org.ammonium.smple.command.moderation.MuteCommand;
import org.ammonium.smple.command.moderation.misc.PunishCommand;
import org.ammonium.smple.command.moderation.misc.SpectateCommand;
import org.ammonium.smple.command.warps.BackCommand;
import org.ammonium.smple.command.warps.RTPCommand;
import org.ammonium.smple.command.warps.home.DelHomeCommand;
import org.ammonium.smple.command.warps.home.HomeCommand;
import org.ammonium.smple.command.warps.home.SetHomeCommand;
import org.ammonium.smple.command.warps.pwarp.DelPWarpCommand;
import org.ammonium.smple.command.warps.pwarp.PWarpCommand;
import org.ammonium.smple.command.warps.pwarp.SetPWarpCommand;
import org.ammonium.smple.command.warps.spawn.SetSpawnCommand;
import org.ammonium.smple.command.warps.spawn.SpawnCommand;
import org.ammonium.smple.command.warps.tp.TPAcceptCommand;
import org.ammonium.smple.command.warps.tp.TPCommand;
import org.ammonium.smple.command.warps.tp.TPDenyCommand;
import org.ammonium.smple.command.warps.tp.TPHereCommand;
import org.ammonium.smple.command.warps.warp.DelWarpCommand;
import org.ammonium.smple.command.warps.warp.SetWarpCommand;
import org.ammonium.smple.command.warps.warp.WarpCommand;
import org.ammonium.smple.command.workbench.AnvilCommand;
import org.ammonium.smple.command.workbench.CartographyCommand;
import org.ammonium.smple.command.workbench.CraftCommand;
import org.ammonium.smple.command.workbench.EChestComand;
import org.ammonium.smple.command.workbench.GrindCommand;
import org.ammonium.smple.command.workbench.LoomCommand;
import org.ammonium.smple.command.workbench.SmithCommand;
import org.ammonium.smple.command.workbench.StonecutterCommand;
import org.ammonium.smple.config.Config;
import org.ammonium.smple.listener.PunishmentListener;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.ammonium.smple.sdk.command.CommandManager;
import org.ammonium.smple.sdk.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmplePlugin extends JavaPlugin {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public final Map<UUID, String> commandLogs = new HashMap<>();

    private ConfigManager configManager;

    private PunishmentService punishmentService;

    @Override
    public void onLoad() {
        this.punishmentService = new PunishmentService();
        this.configManager = new ConfigManager(this);
    }

    @Override
    public void onEnable() {

        // final ETableCommand eTableCommand = new ETableCommand(this);

//        this.getServer().getPluginManager()
//            .registerEvents(
//                this
//            );

        this.configManager.initConfigs(Config.class);

        getServer().getPluginManager().registerEvents(new PunishmentListener(punishmentService), this);

        CommandManager.create(this)
            .withCommands(
                new ExampleCommand(),
                new neofetch(this),
                new SudoCommand(this),
                new DoasCommand(this),
                new CmdHistoryCommand(this),
                new BanCommand(punishmentService),
                new MuteCommand(punishmentService),
                new KickCommand(punishmentService),
                new AnvilCommand(this),
                new CartographyCommand(this),
                new CraftCommand(this),
                new EChestComand(this),
                // eTableCommand,                       // borked
                new GrindCommand(this),
                new LoomCommand(this),
                // new SmeltCommand(this),              // borked
                new SmithCommand(this),
                new StonecutterCommand(this),
                new BackCommand(this),
                new RTPCommand(this),
                new DelHomeCommand(this),
                new HomeCommand(this),
                new SetHomeCommand(this),
                new DelPWarpCommand(this),
                new PWarpCommand(this),
                new SetPWarpCommand(this),
                new SetSpawnCommand(this),
                new SpawnCommand(this),
                new TPAcceptCommand(this),
                new TPCommand(this),
                new TPDenyCommand(this),
                new TPHereCommand(this),
                new DelWarpCommand(this),
                new SetWarpCommand(this),
                new WarpCommand(this),
                new SpectateCommand(),
                new PunishCommand()
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
