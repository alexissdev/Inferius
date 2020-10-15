package dev.notcacha.inferius.bukkit.commands;

import com.google.inject.Inject;
import dev.notcacha.inferius.bukkit.flow.annotation.Language;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.storage.Storage;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

import java.util.Optional;

@Command(names = "warp", permission = "inferius.warp")
public class WarpCommand implements CommandClass {

    @Inject
    private LanguageLib languageLib;
    @Inject
    private Cache<String, Warp> warpCache;
    @Inject
    private Storage<Warp> warpStorage;

    @Command(names = "")
    public boolean main(@Sender Player player, @Language String language, @OptArg String name) {
        Optional<Warp> warp = warpCache.find(name);

        if (name == null || !warp.isPresent()) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.main");

            message.colorize().getMessages(language).forEach(player::sendMessage);
            return true;
        }

        if (!player.hasPermission(warp.get().getPermission())) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.teleport.no-permissions");
            message.colorize()
                    .setVariable("%name%", name);

            player.sendMessage(message.getMessage(language));
            return true;
        }

        player.teleport(warp.get().getLocation());
        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.teleport.message");
        message.colorize()
                .setVariable("%name%", name);
        return true;
    }

    @Command(names = {"create", "add"}, permission = "inferius.warp.create")
    public boolean create(@Sender Player player, @Language String language, String warpName) {
        if (warpCache.exists(warpName)) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.exists");
            message.colorize().setVariable("%name%", warpName);

            player.sendMessage(message.getMessage(language));
            return true;
        }

        warpCache.add(warpName, Warp.builder(warpName).setLocation(player.getLocation()).build());

        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.create");
        message.colorize().setVariable("%name%", warpName);

        player.sendMessage(message.getMessage(language));
        return true;
    }

    @Command(names = {"delete", "remove"}, permission = "inferius.warp.remove")
    public boolean delete(@Sender Player player, @Language String language, String warpName) {
        Optional<Warp> warp = warpCache.find(warpName);

        if (!warp.isPresent()) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.not-exists");
            message.colorize().setVariable("%name%", warpName);

            player.sendMessage(message.getMessage(language));
            return true;
        }

        warpCache.remove(warp.get().getId());
        warpStorage.deleteAsync(warp.get());

        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.delete");
        message.colorize().setVariable("%name%", warpName);

        player.sendMessage(message.getMessage(language));
        return true;
    }

    @Command(names = "list")
    public boolean list(@Sender Player player, @Language String language) {
        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("warp.list");

        message.colorize()
                .setVariable(
                        "%warps%",
                        warpCache.get().values().toString()
                                .replace("[", "")
                                .replace("]", "")
                );

        player.sendMessage(message.getMessage(language));
        return true;
    }
}
