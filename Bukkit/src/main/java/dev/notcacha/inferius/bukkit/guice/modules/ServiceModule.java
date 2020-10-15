package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import dev.notcacha.inferius.bukkit.service.InferiusService;
import dev.notcacha.inferius.bukkit.service.LoaderService;
import dev.notcacha.inferius.bukkit.service.SpawnService;
import dev.notcacha.inferius.bukkit.service.WarpService;
import dev.notcacha.inferius.service.Service;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(Service.class).annotatedWith(Names.named("inferius-service")).to(InferiusService.class);
        this.bind(Service.class).annotatedWith(Names.named("loader-service")).to(LoaderService.class);
        this.bind(Service.class).annotatedWith(Names.named("warp-service")).to(WarpService.class);
        this.bind(Service.class).annotatedWith(Names.named("spawn-service")).to(SpawnService.class);
    }
}
