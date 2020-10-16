package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.notcacha.inferius.bukkit.adapter.LocationGsonAdapter;
import dev.notcacha.inferius.bukkit.adapter.PunishmentGsonAdapter;
import dev.notcacha.inferius.bukkit.adapter.UserGsonAdapter;
import dev.notcacha.inferius.bukkit.adapter.WarpGsonAdapter;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.warp.Warp;
import org.bukkit.Location;

public class GsonModule extends AbstractModule {

    @Override
    public void configure() {
        this.bind(Gson.class).toProvider(
                () ->
                        new GsonBuilder().setPrettyPrinting()
                                .serializeNulls()
                                .registerTypeAdapter(Warp.class, new WarpGsonAdapter())
                                .registerTypeAdapter(Location.class, new LocationGsonAdapter())
                                .registerTypeAdapter(User.class, new UserGsonAdapter())
                                .registerTypeAdapter(Punishment.class, new PunishmentGsonAdapter())
                                .create())
                .in(Scopes.SINGLETON);
    }
}
