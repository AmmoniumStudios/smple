package org.ammonium.smple.sdk.api.model.log;

import java.util.UUID;

public interface Log {

    UUID CONSOLE_ID = UUID.fromString("00000000-0000-0000-0000-000000000000"); // Console ID

    UUID uniqueId();

    UUID emitterId();

    LogType logType();

    String logMessage();

    long timestamp();

    enum LogType {
        COMMAND,
    }

}
