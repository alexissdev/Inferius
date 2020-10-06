package dev.notcacha.inferius.bukkit.flow;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.translator.TranslationProvider;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class InferiusTranslationProvider implements TranslationProvider {

    @Inject
    private LanguageLib languageLib;

    @Inject
    private LanguageUtils languageUtils;

    private final Map<String, String> translationsMap;

    public InferiusTranslationProvider() {
        this.translationsMap = new HashMap<>();

        translationsMap.put("argument.no-more", "No more arguments were found, size: %s position: %s");
    }

    @Override
    public String getTranslation(Namespace namespace, String key) {

        String language = languageUtils.getLanguageFromCommandSender(
                namespace.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE)
        );

        switch (key) {

            case "sender.only-player" : {
                TranslatableMessage message = languageLib.getTranslationManager()
                        .getTranslation("general.only-player");

                return message.colorize().getMessage(language);
            }

            case "sender.unknown" : {
                TranslatableMessage message = languageLib.getTranslationManager()
                        .getTranslation("flow.unknown-sender");

                return message.colorize().getMessage(language);
            }

            case "player.offline" : {
                TranslatableMessage message = languageLib.getTranslationManager()
                        .getTranslation("general.target-offline");

                return message.colorize().setVariable("%target_name%", "%s").getMessage(language);
            }

            case "command.no-permission" : {
                TranslatableMessage message = languageLib.getTranslationManager()
                        .getTranslation("general.no-permission");

                return message.colorize().getMessage(language);
            }

            case "command.subcommand.invalid" : {
                TranslatableMessage message = languageLib.getTranslationManager()
                        .getTranslation("flow.invalid-command");

                return message.colorize().setVariable("%subcommand_name%", "%s").getMessage(language);
            }

            default : {
                return translationsMap.get(key);
            }
        }
    }
}
