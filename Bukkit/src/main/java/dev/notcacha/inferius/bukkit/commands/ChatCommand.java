package dev.notcacha.inferius.bukkit.commands;

import com.google.inject.Inject;
import dev.notcacha.inferius.bukkit.chat.ChatManager;
import dev.notcacha.inferius.bukkit.flow.annotation.Language;
import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@Command(names = "chat", permission = "inferius.chat")
public class ChatCommand implements CommandClass {

    @Inject
    private LanguageLib languageLib;
    @Inject
    private LanguageUtils languageUtils;
    @Inject
    private ChatManager chatManager;

    @Command(names = "")
    public boolean main(CommandSender sender, @Language String language) {
        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("chat.main");

        message.colorize().getMessages(language).forEach(sender::sendMessage);
        return true;
    }

    @Command(names = "clear", permission = "inferius.chat.clear")
    public boolean clear(CommandSender sender) {
        for (int i = 0; i < 150; i++) {
            Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(
                    "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n"
            ));
        }

        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("chat.clear");

        message.colorize().setVariable("%name%", sender.getName());

        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(
                message.getMessage(languageUtils.getLanguageFromPlayer(player))
        ));
        return true;
    }

    @Command(names = "mute", permission = "inferius.chat.mute")
    public boolean mute(CommandSender sender) {

        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("chat.muted.set");

        String state;

        if (chatManager.isMuted()) {
            chatManager.setMuted(false);
            state = "un-muted";
        } else {
            chatManager.setMuted(true);
            state = "muted";
        }

        message.colorize()
                .setVariable("%state%", state)
                .setVariable("%name%", sender.getName());

        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(message.getMessage(
                languageUtils.getLanguageFromPlayer(player))
        ));
        return true;
    }

    @Command(names = "delay", permission = "inferius.chat.delay")
    public boolean delay(CommandSender sender, Long time) {
        chatManager.setDelay(time);

        TranslatableMessage message = languageLib.getTranslationManager().getTranslation("delay.set");

        message.colorize().setVariable("%delay%", String.valueOf(time));

        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(
                message.getMessage(languageUtils.getLanguageFromPlayer(player))
        ));
        return true;
    }
}
