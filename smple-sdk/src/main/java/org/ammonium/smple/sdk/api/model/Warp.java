package org.ammonium.smple.sdk.api.model;

import org.ammonium.smple.sdk.util.GsonProvider;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public record Warp(
    String name,
    String world,
    int x,
    int y,
    int z
) {

    public static Warp fromJson(String json) {
        return GsonProvider.fromJson(json, Warp.class);
    }

    public String toJson() {
        return GsonProvider.toJson(this);
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

}
