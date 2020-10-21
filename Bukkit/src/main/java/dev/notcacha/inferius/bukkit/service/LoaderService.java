package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.service.Service;

public class LoaderService implements Service {

    @Inject
    private Inferius plugin;

    @Inject
    @Named("listener-loader")
    private Service listenerLoader;
    @Inject
    @Named("command-loader")
    private Service commandLoader;

    @Override
    public void start() {
        listenerLoader.start();
        commandLoader.start();
    }

    @Override
    public void stop() {
        plugin.getLogger().info("Commands and Listener has been unloaded!");
    }
}
