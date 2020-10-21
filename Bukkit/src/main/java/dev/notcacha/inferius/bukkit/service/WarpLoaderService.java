package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.model.load.ModelLoad;
import dev.notcacha.inferius.service.Service;

import java.io.File;

@Singleton
public class WarpLoaderService implements Service {

    @Inject
    private Inferius plugin;

    @Inject
    private ModelLoad<Warp> warpLoader;

    @Override
    public void start() {
        File folder = new File(plugin.getDataFolder(), "/warps/");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }

            warpLoader.loadSync(file.getName().replace(".json", ""));
        }
    }

}
