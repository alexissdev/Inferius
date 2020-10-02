package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import dev.notcacha.inferius.bukkit.loader.ListenerLoader;
import dev.notcacha.inferius.loader.Loader;

public class LoaderModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(Loader.class).annotatedWith(Names.named("listener-loader")).to(ListenerLoader.class);
    }
}
