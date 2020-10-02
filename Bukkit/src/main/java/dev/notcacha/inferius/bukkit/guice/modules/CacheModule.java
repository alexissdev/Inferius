package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.user.cache.UserCache;
import dev.notcacha.inferius.cache.Cache;

public class CacheModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<Cache<String, User>>() {}).to(UserCache.class);
    }
}
