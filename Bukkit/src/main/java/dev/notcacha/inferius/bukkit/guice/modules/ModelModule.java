package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.guice.modules.models.ModelDeleteModule;
import dev.notcacha.inferius.bukkit.guice.modules.models.ModelFindModule;
import dev.notcacha.inferius.bukkit.guice.modules.models.ModelLoaderModule;
import dev.notcacha.inferius.bukkit.guice.modules.models.ModelSaveModule;

public class ModelModule extends AbstractModule {

    private final Inferius inferius;
    private final boolean useDatabase;

    public ModelModule(Inferius inferius, boolean useDatabase) {
        this.inferius = inferius;
        this.useDatabase = useDatabase;
    }

    @Override
    protected void configure() {
        this.install(new ModelFindModule(inferius, useDatabase));
        this.install(new ModelSaveModule(inferius, useDatabase));
        this.install(new ModelDeleteModule(inferius, useDatabase));
        this.install(new ModelLoaderModule(useDatabase));
    }
}
