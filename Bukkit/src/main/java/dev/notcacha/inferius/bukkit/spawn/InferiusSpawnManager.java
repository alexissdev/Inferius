package dev.notcacha.inferius.bukkit.spawn;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.json.JsonFileCreator;
import org.bukkit.Location;

import java.io.IOException;
import java.util.Optional;

@Singleton
public class InferiusSpawnManager implements SpawnManager {

    @Inject
    private Inferius plugin;
    @Inject
    private Gson gson;

    private Location location;

    public InferiusSpawnManager() {
        this.location = null;
    }

    @Override
    public Optional<Location> get() {
        return Optional.ofNullable(this.location);
    }

    @Override
    public void set(Location location) {
        this.location = location;
    }

    @Override
    public void load() {
        JsonFileCreator file = JsonFileCreator.create(plugin.getDataFolder(), "spawn.json");

        try {
            this.location = gson.fromJson(file.getJsonString(), Location.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred while trying to create the spawn location", e);
        }
    }

    @Override
    public void save() {
        if (location == null) {
            return;
        }
        JsonFileCreator file = JsonFileCreator.create(plugin.getDataFolder(), "spawn.json");

        try {
            file.writeJson(gson.toJson(location));
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred when trying to save the spawn in JSON format", e);
        }
    }
}
