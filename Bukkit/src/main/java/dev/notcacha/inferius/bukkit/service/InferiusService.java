package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import dev.notcacha.inferius.service.Service;

@Singleton
public class InferiusService implements Service {

    @Inject
    @Named("storage-service")
    private Service storageService;

    @Inject
    @Named("loader-service")
    private Service loaderService;

    @Override
    public void start() {
        storageService.start();
        loaderService.start();
    }

    @Override
    public void stop() {
        storageService.stop();
        loaderService.stop();
    }
}
