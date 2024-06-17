package org.ammonium.smple.sdk;

import lombok.Getter;
import org.ammonium.smple.sdk.api.service.impl.HomeService;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.ammonium.smple.sdk.api.service.impl.UserService;
import org.ammonium.smple.sdk.storage.sql.SqlStorageFactory;

@Getter
public final class SmpleSdk {

    private static final String ENDPOINT = "https://api.smpleproject.org";

    private static SmpleSdk instance;

    public static SmpleSdk get() {
        if (instance == null) {
            instance = new SmpleSdk();
        }
        return instance;
    }

    private final SqlStorageFactory storageFactory = new SqlStorageFactory();

    private final UserService userService = new UserService(storageFactory);

    private final HomeService homeService = new HomeService(storageFactory);

    private final PunishmentService punishmentService = new PunishmentService(storageFactory);

    private SmpleSdk() {
        SmpleSdk.instance = this;
    }


}
