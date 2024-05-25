package org.ammonium.smple.sdk.api.model.log;

import java.util.UUID;

public record CommandLog(
    UUID uniqueId,
    UUID emitterId,
    String logMessage,
    long timestamp
) implements Log {

    @Override
    public LogType logType() {
        return LogType.COMMAND;
    }
}
