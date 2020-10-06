package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.bukkit.BukkitLanguageLib;

public class LanguageModule extends AbstractModule {

    private final Inferius plugin;

    public LanguageModule(Inferius plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        this.bind(LanguageLib.class).toProvider(() -> {
            LanguageLib languageLib = BukkitLanguageLib.builder(
                    plugin, "en", true)
                    .build();
            languageLib.getFileManageable().add("es", true);

            return languageLib;
        }).in(Scopes.SINGLETON);
    }
}
