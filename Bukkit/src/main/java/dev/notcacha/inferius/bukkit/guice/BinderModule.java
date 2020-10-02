package dev.notcacha.inferius.bukkit.guice;

import com.google.inject.AbstractModule;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.guice.modules.*;

public class BinderModule extends AbstractModule {

    private final Inferius inferius;

    public BinderModule(Inferius inferius) {
        this.inferius = inferius;
    }

    @Override
    protected void configure() {
        this.bind(Inferius.class).toInstance(this.inferius);

        this.install(new CacheModule());
        this.install(new StorageModule());
        this.install(new ManageableModule());
        this.install(new LoaderModule());
        this.install(new ServiceModule());
    }
}
