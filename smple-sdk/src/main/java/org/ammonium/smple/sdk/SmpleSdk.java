package org.ammonium.smple.sdk;

import org.ammonium.smple.sdk.api.service.impl.HomeService;
import org.ammonium.smple.sdk.api.service.impl.LogService;
import org.ammonium.smple.sdk.api.service.impl.PlayerWarpService;
import org.ammonium.smple.sdk.api.service.impl.PunishmentService;
import org.ammonium.smple.sdk.api.service.impl.UserService;
import org.ammonium.smple.sdk.api.service.impl.WarpService;

public final class SmpleSdk {

    private static SmpleSdk instance;

    public static SmpleSdk get() {
        if (instance == null) {
            instance = new SmpleSdk();
        }
        return instance;
    }

    private final UserService userService = new UserService();
    private final HomeService homeService = new HomeService();
    private final PlayerWarpService playerWarpService = new PlayerWarpService();
    private final WarpService warpService = new WarpService();
    private final PunishmentService punishmentService = new PunishmentService();
    private final LogService logService = new LogService();

    public HomeService getHomeService() {
        return homeService;
    }

    public PlayerWarpService getPlayerWarpService() {
        return playerWarpService;
    }

    public PunishmentService getPunishmentService() {
        return punishmentService;
    }

    public UserService getUserService() {
        return userService;
    }

    public WarpService getWarpService() {
        return warpService;
    }

    public LogService getLogService() {
        return logService;
    }

    private SmpleSdk() {
        SmpleSdk.instance = this;
    }

}
