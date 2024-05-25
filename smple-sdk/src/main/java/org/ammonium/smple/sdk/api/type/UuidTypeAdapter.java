package org.ammonium.smple.sdk.api.type;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.UUID;

public final class UuidTypeAdapter extends TypeAdapter<UUID> {

    @Override
    public void write(JsonWriter writer, UUID uuid) throws IOException {
        writer.value(uuid.toString());
    }

    @Override
    public UUID read(JsonReader reader) throws IOException {
        return UUID.fromString(reader.nextString());
    }
}
