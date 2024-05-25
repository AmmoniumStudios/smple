package org.ammonium.smple.sdk.config.serializer;

import java.lang.reflect.Type;
import java.util.function.Predicate;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.spongepowered.configurate.serialize.ScalarSerializer;
import org.spongepowered.configurate.serialize.SerializationException;

public class ComponentSerializer extends ScalarSerializer<Component> {

    private static final ComponentSerializer INSTANCE = new ComponentSerializer();
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private ComponentSerializer() {
        super(Component.class);
    }

    public static ComponentSerializer get() {
        return INSTANCE;
    }

    @Override
    public Component deserialize(Type type, Object obj) throws SerializationException {
        return MINI_MESSAGE.deserialize(obj.toString());
    }

    @Override
    protected Object serialize(Component item, Predicate<Class<?>> typeSupported) {
        return MINI_MESSAGE.serialize(item);
    }
}
