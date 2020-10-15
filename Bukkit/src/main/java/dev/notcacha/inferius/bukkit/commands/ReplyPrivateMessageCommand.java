package dev.notcacha.inferius.bukkit.commands;

import com.google.inject.Inject;
import dev.notcacha.inferius.bukkit.flow.annotation.Language;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Text;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class ReplyPrivateMessageCommand implements CommandClass {

    @Inject
    private LanguageLib languageLib;
    @Inject
    private LanguageUtils languageUtils;
    @Inject
    private Cache<UUID, User> userCache;

    @Command(names = {"reply", "r"}, permission = "inferius.privatemessage")
    public boolean main(@Sender Player player, @Language String language, @Text String privateMessage) {
        Optional<User> user = userCache.find(player.getUniqueId());
        if (!user.isPresent()) {
            return false;
        }

        if (privateMessage.isEmpty()) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("private-message.main");

            player.sendMessage(message.colorize().getMessage(language));
            return true;
        }

        Optional<String> lastAuthor = user.get().getLastAuthorPrivateMessage();
        if (!lastAuthor.isPresent()) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("private-message.not-last-author");

            player.sendMessage(message.colorize().getMessage(language));
            return true;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(lastAuthor.get());

        if (target.getPlayer() == null) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("general.target-offline");
            message.colorize().setVariable("%target_name%", target.getName());

            player.sendMessage(message.getMessage(language));
            user.get().setLastAuthorPrivateMessage(null);

            return true;
        }

        TranslatableMessage toMessage = languageLib.getTranslationManager().getTranslation("private-message.to");
        toMessage.colorize().setVariable("%target_name%", target.getName());
        player.sendMessage(toMessage.getMessage(language));

        TranslatableMessage fromMessage = languageLib.getTranslationManager().getTranslation("private-message.from");
        fromMessage.colorize().setVariable("%player_name%", player.getName());

        target.getPlayer().sendMessage(fromMessage.getMessage(languageUtils.getLanguageFromPlayer(target.getPlayer())));
        user.get().setLastAuthorPrivateMessage(target.getName());
        return true;
    }
}
