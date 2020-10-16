package dev.notcacha.inferius.bukkit.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.cache.Cache;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class LanguageUtils {

    @Inject
    private Cache<UUID, User> userCache;

    private final String DEFAULT_LANGUAGE = "en";

    public String getLanguageFromCommandSender(CommandSender sender) {

        if (sender instanceof Player) {
            return getLanguageFromPlayer((Player) sender);
        }

        return DEFAULT_LANGUAGE;
    }

    public String getLanguageFromPlayer(Player player) {
        String language = DEFAULT_LANGUAGE;

        Optional<User> user = userCache.find(player.getUniqueId());

        if (user.isPresent()) {
            language = user.get().getLanguage();
        }

        return language;
    }

}
