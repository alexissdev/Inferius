package dev.notcacha.inferius.bukkit.user.cache;

import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Singleton
public class UserCache implements Cache<UUID, User> {

    private final Map<UUID, User> userMap;

    public UserCache() {
        this.userMap = new HashMap<>();
    }

    @Override
    public Map<UUID, User> get() {
        return this.userMap;
    }
}
