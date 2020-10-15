package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.notcacha.inferius.bukkit.adapter.WarpAdapter;
import dev.notcacha.inferius.bukkit.warp.Warp;

public class GsonModule extends AbstractModule {

    @Override
    public void configure() {
        this.bind(Gson.class).toProvider(
                () ->
                        new GsonBuilder().setPrettyPrinting()
                                .serializeNulls()
                                .registerTypeAdapter(Warp.class, new WarpAdapter())
                                .create())
                .in(Scopes.SINGLETON);
    }
}