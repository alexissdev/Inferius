package dev.notcacha.inferius.bukkit.loader;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.loader.Loader;
import dev.notcacha.inferius.storage.Storage;

import java.io.File;

@Singleton
public class WarpLoader implements Loader {

    @Inject
    private Inferius plugin;

    @Inject
    private Storage<Warp> warpStorage;

    @Override
    public void load() {
        File folder = new File(plugin.getDataFolder(), "/warps/");

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }

            warpStorage.load(file.getName().replace(".json", ""));
        }
    }

}
