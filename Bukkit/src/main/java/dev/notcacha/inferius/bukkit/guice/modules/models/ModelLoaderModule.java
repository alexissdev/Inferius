package dev.notcacha.inferius.bukkit.guice.modules.models;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.model.load.ModelLoad;
import dev.notcacha.inferius.model.load.SimpleModelLoad;

public class ModelLoaderModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<ModelLoad<User>>() {}).toProvider(SimpleModelLoad::new);
        this.bind(new TypeLiteral<ModelLoad<Punishment>>() {}).toProvider(SimpleModelLoad::new);
        this.bind(new TypeLiteral<ModelLoad<Warp>>() {}).toProvider(SimpleModelLoad::new);
    }
}
