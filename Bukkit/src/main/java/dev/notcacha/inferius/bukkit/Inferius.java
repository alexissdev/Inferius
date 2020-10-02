package dev.notcacha.inferius.bukkit;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import dev.notcacha.inferius.bukkit.guice.BinderModule;
import dev.notcacha.inferius.service.Service;
import org.bukkit.plugin.java.JavaPlugin;

public final class Inferius extends JavaPlugin {

    @Inject
    @Named("inferius-service")
    private Service inferiusService;

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new BinderModule(this));
        injector.injectMembers(this);

        inferiusService.start();
    }

    @Override
    public void onDisable() {
        inferiusService.stop();
    }
}
