package org.ammonium.smple.sdk.config;

import java.nio.file.WatchEvent;
import java.util.function.Consumer;

public interface Config {

    default Consumer<WatchEvent<?>> onUpdate() {
        return e -> { };
    }

}