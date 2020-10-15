package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import dev.notcacha.inferius.service.Service;

@Singleton
public class InferiusService implements Service {

    @Inject
    @Named("loader-service")
    private Service loaderService;

    @Inject
    @Named("warp-service")
    private Service warpService;
    @Override
    public void start() {
        loaderService.start();
        warpService.start();
    }

    @Override
    public void stop() {
        loaderService.stop();
        warpService.stop();
    }
}
