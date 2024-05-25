package org.ammonium.smple.sdk.command;

import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    private CommandManager(final Plugin plugin) {
        super(plugin, ExecutionCoordinator.asyncCoordinator(), SenderMapper.identity());
        this.annotationParser = new AnnotationParser<>(this, CommandSender.class);

        // Override existing commands for our plugin to avoid conflicts with other plugins
        settings().set(ManagerSetting.OVERRIDE_EXISTING_COMMANDS, true);
    }

    public static CommandManager create(final Plugin plugin) {
        return new CommandManager(plugin);
    }

    public CommandManager withCommands(Object... commands) {
        for (final Object object : commands) {
            this.annotationParser.parse(object);
        }
        return this;
    }

    public CommandManager withCommands(String pkg, Object... injectables) {
        final Map<Class<?>, Object> injectableMap = Arrays.stream(injectables)
            .collect(
                Collectors.toMap(Object::getClass,
                    Function.identity())
            );

        try {
            ClassPath.from(owningPlugin().getClass().getClassLoader())
                .getTopLevelClassesRecursive(pkg)
                .stream()
                .map(ClassPath.ClassInfo::load)
                .forEach(clazz -> {
                    try {
                        Object[] instances = Arrays.stream(clazz.getDeclaredConstructors()[0].getParameters())
                            .map(Parameter::getType)
                            .map(type -> {
                                Object injectable = injectableMap.get(type);
                                if (injectable != null) {
                                    return injectable;
                                }

                                throw new RuntimeException("No injectable found for type " + type);
                            })
                            .toArray();

                        annotationParser.parse(clazz.getDeclaredConstructors()[0].newInstance(instances));
                    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

}

