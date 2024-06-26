package org.ammonium.smple.sdk.api.model;

import java.util.UUID;
import org.ammonium.smple.sdk.util.GsonProvider;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public record Home(
    UUID ownerId,
    String name,
    String world,
    int x,
    int y,
    int z
) {

    public static Home fromString(String json) {
        return GsonProvider.fromJson(json, Home.class);
    }

    public String toJson() {
        return GsonProvider.toJson(this);
    }
    
    public Location toLocation(){
        World targetWorld = Bukkit.getWorld(this.world);
        return new Location(targetWorld, this.x, this.y, this.z);
    }

}
