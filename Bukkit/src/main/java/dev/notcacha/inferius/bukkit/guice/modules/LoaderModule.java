package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import dev.notcacha.inferius.bukkit.loader.CommandLoader;
import dev.notcacha.inferius.bukkit.loader.ListenerLoader;
import dev.notcacha.inferius.bukkit.loader.WarpLoader;
import dev.notcacha.inferius.loader.Loader;

public class LoaderModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(Loader.class).annotatedWith(Names.named("listener-loader")).to(ListenerLoader.class);
        this.bind(Loader.class).annotatedWith(Names.named("command-loader")).to(CommandLoader.class);
        this.bind(Loader.class).annotatedWith(Names.named("warp-loader")).to(WarpLoader.class);
    }
}
