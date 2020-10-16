package dev.notcacha.inferius.bukkit.commands;

import com.google.inject.Inject;

import dev.notcacha.inferius.bukkit.spawn.SpawnManager;
import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
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
    private SpawnManager spawnManager;
    @Inject
    private LanguageUtils languageUtils;

    @Command(names = "spawn")
    public boolean main(@Sender Player player) {
        String language = languageUtils.getLanguageFromPlayer(player);
        Optional<Location> location = spawnManager.get();

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
