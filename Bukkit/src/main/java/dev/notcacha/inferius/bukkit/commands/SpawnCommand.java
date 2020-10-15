package dev.notcacha.inferius.bukkit.commands;

import com.google.inject.Inject;
import dev.notcacha.inferius.bukkit.flow.annotation.Language;
import dev.notcacha.inferius.manageable.ObjectManageable;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

public class SpawnCommand implements CommandClass {

    @Inject
    private LanguageLib languageLib;
    @Inject
    private ObjectManageable<Location> spawnManageable;

    @Command(names = "spawn")
    public boolean main(@Sender Player player, @Language String language) {
        Optional<Location> location = spawnManageable.get();

        if (!location.isPresent()) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("spawn.not-exists");

            player.sendMessage(message.colorize().getMessage(language));
            return true;
        }

        player.teleport(location.get());
        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("spawn.teleport");

        player.sendMessage(message.colorize().getMessage(language));
        return true;
    }
}
