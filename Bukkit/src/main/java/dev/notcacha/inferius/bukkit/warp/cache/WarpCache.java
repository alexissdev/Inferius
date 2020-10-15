package dev.notcacha.inferius.bukkit.warp.cache;

import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.cache.Cache;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class WarpCache implements Cache<String, Warp> {

    private final Map<String, Warp> warpMap;

    public WarpCache() {
        this.warpMap = new HashMap<>();
    }

    @Override
    public Map<String, Warp> get() {
        return warpMap;
    }
}
