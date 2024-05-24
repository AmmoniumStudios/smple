package org.ammonium.smple.sdk.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.incendo.cloud.SenderMapper;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.setting.ManagerSetting;

/**
 * A command manager for the plugin.
 */
public final class CommandManager extends PaperCommandManager<CommandSender> {

    private final AnnotationParser<CommandSender> annotationParser;

    public static CommandManager create(final Plugin plugin) {
        return new CommandManager(plugin);
    }

    private CommandManager(final Plugin plugin) {
        super(plugin, ExecutionCoordinator.asyncCoordinator(), SenderMapper.identity());
        this.annotationParser = new AnnotationParser<>(this, CommandSender.class);

        // Override existing commands for our plugin to avoid conflicts with other plugins
        settings().set(ManagerSetting.OVERRIDE_EXISTING_COMMANDS, true);
    }

    public CommandManager withCommands(Object... commands) {
        for (final Object object : commands) {
            this.annotationParser.parse(object);
        }
        return this;
    }

}

