package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.cache.BaseCache;
import dev.notcacha.inferius.cache.Cache;

public class CacheModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<Cache<String, User>>() {}).toProvider(BaseCache::new);
        this.bind(new TypeLiteral<Cache<String, Warp>>() {}).toProvider(BaseCache::new);
        this.bind(new TypeLiteral<Cache<String, Long>>() {}).toProvider(BaseCache::new);
    }
}
