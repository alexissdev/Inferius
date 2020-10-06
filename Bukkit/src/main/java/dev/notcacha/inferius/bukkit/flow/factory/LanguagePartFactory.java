package dev.notcacha.inferius.bukkit.flow.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.flow.part.LanguagePart;
import dev.notcacha.inferius.bukkit.utils.LanguageUtils;
import me.fixeddev.commandflow.annotated.part.PartFactory;
import me.fixeddev.commandflow.part.CommandPart;

import java.lang.annotation.Annotation;
import java.util.List;

@Singleton
public class LanguagePartFactory implements PartFactory {

    @Inject
    private LanguageUtils languageUtils;


    @Override
    public CommandPart createPart(String name, List<? extends Annotation> modifiers) {
        return new LanguagePart(name, languageUtils);
    }
}
