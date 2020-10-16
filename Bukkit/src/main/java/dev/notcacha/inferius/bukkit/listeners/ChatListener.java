package dev.notcacha.inferius.bukkit.listeners;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import dev.notcacha.inferius.bukkit.chat.ChatManager;
import dev.notcacha.inferius.bukkit.utils.CooldownType;
import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.formatter.Formatter;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Optional;

public class ChatListener implements Listener {

    @Inject
    @Named("chat-formatter")
    private Formatter<Player> chatFormatter;
    @Inject
    private LanguageLib languageLib;
    @Inject
    private LanguageUtils languageUtils;
    @Inject
    private ChatManager chatManager;
    @Inject
    private Cache<String, Long> cooldownCache;

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String language = languageUtils.getLanguageFromPlayer(player);

        if (chatManager.isMuted()) {
            if (!player.hasPermission("inferius.chat.mute.bypass")) {
                TranslatableMessage message = languageLib.getTranslationManager().getTranslation("chat.muted.is");

                player.sendMessage(message.colorize().getMessage(language));

                return;
            }
        }

        if (chatManager.hasDelayed()) {
            if (!player.hasPermission("inferius.chat.delay.bypass")) {
                Optional<Long> time = cooldownCache.find(CooldownType.CHAT_DELAY.getAndReplaceId(
                        player.getUniqueId().toString()
                ));

                if (time.isPresent() && time.get() > 0) {
                    TranslatableMessage message = languageLib.getTranslationManager().getTranslation("chat.delay.contains");

                    message.colorize()
                            .setVariable("%cooldown%", String.valueOf(time.get()));

                    player.sendMessage(message.getMessage(language));
                    return;
                }
            }
        }

        String message = event.getMessage();
        String format = chatFormatter.format(player, message);
        event.setFormat("%2$s");
        event.setMessage(format);
    }
}
