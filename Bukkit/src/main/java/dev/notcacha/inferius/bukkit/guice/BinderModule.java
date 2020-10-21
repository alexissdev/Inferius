package dev.notcacha.inferius.bukkit.guice;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.file.FileCreator;
import dev.notcacha.inferius.bukkit.guice.modules.CacheModule;
import dev.notcacha.inferius.bukkit.guice.modules.FormatterModule;
import dev.notcacha.inferius.bukkit.guice.modules.GsonModule;
import dev.notcacha.inferius.bukkit.guice.modules.LanguageModule;
import dev.notcacha.inferius.bukkit.guice.modules.ManagerModule;
import dev.notcacha.inferius.bukkit.guice.modules.ModelModule;
import dev.notcacha.inferius.bukkit.guice.modules.MongoModule;
import dev.notcacha.inferius.bukkit.guice.modules.ServiceModule;
import dev.notcacha.inferius.bukkit.guice.modules.StorageModule;

import java.util.concurrent.Executors;

public class BinderModule extends AbstractModule {

    private final Inferius inferius;
    private final FileCreator config;

    public BinderModule(Inferius inferius) {
        this.inferius = inferius;
        this.config =  new FileCreator(inferius, "config.yml");
    }

    @Override
    protected void configure() {
        this.bind(Inferius.class).toInstance(this.inferius);
        this.bind(ListeningExecutorService.class).toProvider(
                () ->
                        MoreExecutors.listeningDecorator(Executors.newCachedThreadPool()))
                .in(Scopes.SINGLETON);

        this.install(new LanguageModule(inferius));
        this.install(new MongoModule(config));
        this.install(new ModelModule(inferius, config.getBoolean("mongo.use")));
        this.install(new CacheModule());
        this.install(new GsonModule());
        this.install(new StorageModule());
        this.install(new ManagerModule());
        this.install(new FormatterModule());
        this.install(new ServiceModule());
    }

}
