package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.storage.UserStorage;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.storage.Storage;

public class StorageModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<Storage<User>>() {}).to(UserStorage.class);
    }
}
