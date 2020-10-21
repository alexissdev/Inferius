package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.listeners.ChatListener;
import dev.notcacha.inferius.bukkit.listeners.UserListener;
import dev.notcacha.inferius.service.Service;
import org.bukkit.event.Listener;

@Singleton
public class ListenerLoaderService implements Service {

    @Inject
    private Inferius plugin;

    @Inject
    private UserListener userListener;
    @Inject
    private ChatListener chatListener;

    private void loadAll(Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @Override
    public void start() {
        loadAll(
                userListener,
                chatListener
        );
    }
}
