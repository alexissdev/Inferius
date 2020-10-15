package dev.notcacha.inferius.bukkit.cooldown;

import com.google.inject.Singleton;
import dev.notcacha.inferius.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class CooldownCache implements Cache<String, Long> {

    private final Map<String, Long> cooldownMap;

    public CooldownCache() {
        this.cooldownMap = new HashMap<>();
    }

    @Override
    public Map<String, Long> get() {
        return this.cooldownMap;
    }

    @Override
    public Optional<Long> find(String id) {
        Long time = get().get(id);

        if (time == null) {
            return Optional.empty();
        }

        return Optional.of(((System.currentTimeMillis() / 1000) + time) - (System.currentTimeMillis() / 1000));
    }
}
