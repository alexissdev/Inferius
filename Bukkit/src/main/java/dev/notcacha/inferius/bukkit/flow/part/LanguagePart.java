package dev.notcacha.inferius.bukkit.flow.part;

import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.part.ArgumentPart;
import me.fixeddev.commandflow.stack.ArgumentStack;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class LanguagePart implements ArgumentPart {

    private final String name;
    private final LanguageUtils languageUtils;

    public LanguagePart(String name, LanguageUtils languageUtils) {
        this.name = name;
        this.languageUtils = languageUtils;
    }


    @Override
    public List<? extends String> parseValue(CommandContext context, ArgumentStack stack)
            throws ArgumentParseException {

        return Collections.singletonList(
                languageUtils.getLanguageFromCommandSender(
                        context.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE)
                )
        );
    }

    @Override
    public Type getType() {
        return String.class;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
