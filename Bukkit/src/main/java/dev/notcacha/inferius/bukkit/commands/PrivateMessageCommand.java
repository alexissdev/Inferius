package dev.notcacha.inferius.bukkit.commands;

import com.google.inject.Inject;

import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Text;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PrivateMessageCommand implements CommandClass {

    @Inject
    private LanguageLib languageLib;
    @Inject
    private LanguageUtils languageUtils;

    @Command(names = {"message", "tell", "msg"}, permission = "inferius.privatemessage")
    public boolean main(@Sender Player player, OfflinePlayer target, @Text String privateMessage) {
        String language = languageUtils.getLanguageFromPlayer(player);
        if (privateMessage.isEmpty()) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("private-message.main");

            player.sendMessage(message.colorize().getMessage(language));
            return true;
        }

        if (target.getPlayer() == null) {
            TranslatableMessage message = languageLib.getTranslationManager().getTranslation("general.target-offline");
            message.colorize().setVariable("%target_name%", target.getName());

            player.sendMessage(message.getMessage(language));
            return true;
        }

        TranslatableMessage toMessage = languageLib.getTranslationManager().getTranslation("private-message.to");
        toMessage.colorize().setVariable("%target_name%", target.getName());
        player.sendMessage(toMessage.getMessage(language));

        TranslatableMessage fromMessage = languageLib.getTranslationManager().getTranslation("private-message.from");
        fromMessage.colorize().setVariable("%player_name%", player.getName());

        target.getPlayer().sendMessage(fromMessage.getMessage(languageUtils.getLanguageFromPlayer(target.getPlayer())));
        return true;
    }
}
