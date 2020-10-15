package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.loader.Loader;
import dev.notcacha.inferius.service.Service;
import dev.notcacha.inferius.storage.Storage;

import java.util.HashSet;

@Singleton
public class WarpService implements Service {

    @Inject
    @Named("warp-loader")
    private Loader loader;
    @Inject
    private Cache<String, Warp> warpCache;
    @Inject
    private Storage<Warp> warpStorage;

    @Override
    public void start() {
        loader.load();
    }

    @Override
    public void stop() {
        warpStorage.saveSync(new HashSet<>(warpCache.get().values()));
    }
}
