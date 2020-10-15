package dev.notcacha.inferius.bukkit.guice;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.guice.modules.*;

import java.util.concurrent.Executors;


public class BinderModule extends AbstractModule {

    private final Inferius inferius;

    public BinderModule(Inferius inferius) {
        this.inferius = inferius;
    }

    @Override
    protected void configure() {
        this.bind(Inferius.class).toInstance(this.inferius);
        this.bind(ListeningExecutorService.class).toProvider(
                () ->
                        MoreExecutors.listeningDecorator(Executors.newCachedThreadPool()))
                .in(Scopes.SINGLETON);

        this.install(new LanguageModule(inferius));
        this.install(new CacheModule());
        this.install(new GsonModule());
        this.install(new StorageModule());
        this.install(new ManagerModule());
        this.install(new ManageableModule());
        this.install(new FormatterModule());
        this.install(new LoaderModule());
        this.install(new ServiceModule());
    }
}
