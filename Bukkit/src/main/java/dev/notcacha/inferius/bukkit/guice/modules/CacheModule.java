package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.cooldown.CooldownCache;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.user.cache.UserCache;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.bukkit.warp.cache.WarpCache;
import dev.notcacha.inferius.cache.Cache;

public class CacheModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<Cache<String, User>>() {}).to(UserCache.class);
        this.bind(new TypeLiteral<Cache<String, Warp>>() {}).to(WarpCache.class);
        this.bind(new TypeLiteral<Cache<String, Long>>() {}).to(CooldownCache.class);
    }
}
