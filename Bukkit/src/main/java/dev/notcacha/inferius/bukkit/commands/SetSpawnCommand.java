package dev.notcacha.inferius.bukkit.commands;

import com.google.inject.Inject;
import dev.notcacha.inferius.bukkit.flow.annotation.Language;
import dev.notcacha.inferius.bukkit.placeholder.LocationPlaceholderApplier;
import dev.notcacha.inferius.bukkit.spawn.SpawnManager;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandClass {

    @Inject
    private LanguageLib languageLib;
    @Inject
    private SpawnManager spawnManager;

    @Command(names = "setspawn")
    public boolean main(@Sender Player player, @Language String language) {
        Location location = player.getLocation();

        spawnManager.set(location);
        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("spawn.set");

        message.colorize().addPlaceholder(location, new LocationPlaceholderApplier());

        player.sendMessage(message.getMessage(language));
        return true;
    }
}
