package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.storage.PunishmentStorage;
import dev.notcacha.inferius.bukkit.storage.UserStorage;
import dev.notcacha.inferius.bukkit.storage.WarpStorage;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.storage.Storage;

public class StorageModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<Storage<User>>() {}).to(UserStorage.class);
        this.bind(new TypeLiteral<Storage<Punishment>>() {}).to(PunishmentStorage.class);
        this.bind(new TypeLiteral<Storage<Warp>>() {}).to(WarpStorage.class);
    }
}
