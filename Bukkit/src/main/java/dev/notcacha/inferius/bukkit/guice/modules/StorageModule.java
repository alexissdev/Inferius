package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.storage.BaseStorage;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.storage.Storage;

public class StorageModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<Storage<User>>() {}).toProvider(BaseStorage::new);
        this.bind(new TypeLiteral<Storage<Punishment>>() {}).toProvider(BaseStorage::new);
        this.bind(new TypeLiteral<Storage<Warp>>() {}).toProvider(BaseStorage::new);
    }
}
