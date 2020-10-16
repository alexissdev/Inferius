package dev.notcacha.inferius.bukkit.user.cache;

import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.cache.Cache;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserCache implements Cache<String, User> {

    private final Map<String, User> userMap;

    public UserCache() {
        this.userMap = new HashMap<>();
    }

    @Override
    public Map<String, User> get() {
        return this.userMap;
    }
}
