package org.ammonium.smple.sdk.storage.credentials;

import java.util.Map;
import lombok.Getter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
@Getter
public final class Credentials {

    @Setting
    private String host = "localhost";

    @Setting
    private String username = "smple";

    @Setting
    private String password = "password";

    @Setting
    private String database = "smple";

    @Setting
    private int port = 5432;

    @Setting
    private Map<String, String> configurationOptions = Map.of(
        "ssl", "false"
    );

}
