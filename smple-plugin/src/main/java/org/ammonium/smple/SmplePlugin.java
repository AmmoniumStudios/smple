package org.ammonium.smple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.ammonium.smple.command.misc.CmdHistoryCommand;
import org.ammonium.smple.command.misc.DoasCommand;
import org.ammonium.smple.command.ExampleCommand;
import org.ammonium.smple.command.misc.SudoCommand;
import org.ammonium.smple.command.workbench.*;
import org.ammonium.smple.command.SudoCommand;
import org.ammonium.smple.command.moderation.BanCommand;
import org.ammonium.smple.command.moderation.KickCommand;
import org.ammonium.smple.command.moderation.MuteCommand;
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

    private PunishmentService punishmentService;

    @Override
    public void onLoad() {
        this.punishmentService = new PunishmentService();
    }

    @Override
    public void onEnable() {
        
        // final ETableCommand eTableCommand = new ETableCommand(this);
        
//        this.getServer().getPluginManager()
//            .registerEvents(
//                this
//            );
        
        ConfigManager.getInstance().initConfigs(this, Config.class);

        getServer().getPluginManager().registerEvents(new PunishmentListener(punishmentService), this);

        CommandManager.create(this)
            .withCommands(
                new ExampleCommand(),
                new SudoCommand(this),
                new DoasCommand(this),
                new CmdLogsCommand(this),
                new BanCommand(punishmentService),
                new MuteCommand(punishmentService),
                new KickCommand(punishmentService),
                new MuteCommand(punishmentService)
                new AnvilCommand(this),
                new CartographyCommand(this),
                new CraftCommand(this),
                new EChestComand(this),
                // eTableCommand,                       // borked
                new GrindCommand(this),
                new LoomCommand(this),
                // new SmeltCommand(this),              // borked
                new SmithCommand(this),
                new StonecutterCommand(this)
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
