package dev.notcacha.inferius.bukkit.loader;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.listeners.ChatListener;
import dev.notcacha.inferius.bukkit.listeners.UserListeners;
import dev.notcacha.inferius.loader.Loader;
import org.bukkit.event.Listener;

@Singleton
public class ListenerLoader implements Loader {

    @Inject
    private Inferius plugin;

    @Inject
    private UserListeners userListeners;
    @Inject
    private ChatListener chatListener;

    private void loadAll(Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @Override
    public void load() {
        loadAll(
                userListeners,
                chatListener
        );
    }
}
