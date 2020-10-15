package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.spawn.SpawnManager;
import dev.notcacha.inferius.service.Service;

@Singleton
public class SpawnService implements Service {

    @Inject
    private SpawnManager spawnManager;

    @Override
    public void start() {
        spawnManager.load();
    }

    @Override
    public void stop() {
        spawnManager.save();
    }
}
