package dev.notcacha.inferius.bukkit.listeners;

import com.google.inject.Inject;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.model.load.ModelLoad;
import dev.notcacha.inferius.storage.Storage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class UserListeners implements Listener {

    @Inject
    private Storage<User> userStorage;
    @Inject
    private Cache<UUID, User> userCache;
    @Inject
    private ModelLoad<User> userLoad;

    @EventHandler
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        userLoad.loadAsync(event.getUniqueId().toString());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID id = event.getPlayer().getUniqueId();

        userCache.find(id).ifPresent(user -> userStorage.saveAsync(user));
    }
}
