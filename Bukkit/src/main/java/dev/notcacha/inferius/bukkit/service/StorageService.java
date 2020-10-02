package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.service.Service;
import dev.notcacha.inferius.storage.Storage;

@Singleton
public class StorageService implements Service {

    @Inject
    private Storage<User> userStorage;

    @Override
    public void start() {
        userStorage.loadAll();
    }

    @Override
    public void stop() {
        userStorage.saveAll();
    }
}
