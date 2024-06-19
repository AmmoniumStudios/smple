package org.ammonium.smple.sdk.api.service.impl;

import com.google.gson.reflect.TypeToken;
import org.ammonium.smple.sdk.api.model.Warp;
import org.ammonium.smple.sdk.util.GsonProvider;
import org.bukkit.plugin.Plugin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class WarpService {

    private final Path filePath;

    private final List<Warp> warps;

    public WarpService(Plugin plugin) {
        this.filePath = plugin.getDataFolder().toPath().resolve("warps.json");
        this.warps = new CopyOnWriteArrayList<>();
    }

    public void create(Warp warp) {
        if (this.warps.contains(warp)) {
            throw new IllegalArgumentException("Warp already exists");
        }
        this.warps.add(warp);
    }

    public Warp get(String name) {
        return this.warps.stream()
            .filter(warp -> warp.name().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Warp not found"));
    }

    public void update(Warp warp) {
        if (!this.warps.contains(warp)) {
            throw new IllegalArgumentException("Warp not found");
        }
        this.warps.remove(warp);
        this.warps.add(warp);
    }

    public void delete(String name) {
        Warp warp = this.get(name);
        this.warps.remove(warp);
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(this.filePath.toFile())) {
            writer.write(GsonProvider.GSON.toJson(this.warps));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromFile() {
        try (FileReader reader = new FileReader(this.filePath.toFile())) {
            TypeToken<Warp> typeToken = new TypeToken<>() {};
            this.warps.addAll(GsonProvider.GSON.fromJson(reader, typeToken.getType()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
