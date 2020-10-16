package dev.notcacha.inferius.bukkit.guice.modules.models;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.model.load.JsonModelLoad;
import dev.notcacha.inferius.model.load.ModelLoad;
import dev.notcacha.inferius.model.load.MongoModelLoad;

public class ModelLoaderModule extends AbstractModule {

    private final boolean useDatabase;

    public ModelLoaderModule(boolean useDatabase) {
        this.useDatabase = useDatabase;
    }

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<ModelLoad<User>>() {}).toProvider(
                () -> (!useDatabase) ? new JsonModelLoad<>() : new MongoModelLoad<>());
        this.bind(new TypeLiteral<ModelLoad<Punishment>>() {}).toProvider(
                () -> (!useDatabase) ? new JsonModelLoad<>() : new MongoModelLoad<>());

        this.bind(new TypeLiteral<ModelLoad<Warp>>() {}).toProvider(JsonModelLoad::new);
    }
}
