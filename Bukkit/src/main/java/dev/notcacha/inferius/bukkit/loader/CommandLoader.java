package dev.notcacha.inferius.bukkit.loader;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.commands.*;
import dev.notcacha.inferius.bukkit.flow.InferiusTranslationProvider;
import dev.notcacha.inferius.bukkit.flow.annotation.Language;
import dev.notcacha.inferius.bukkit.flow.factory.LanguagePartFactory;
import dev.notcacha.inferius.loader.Loader;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.SimpleCommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.part.Key;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitAuthorizer;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.command.Command;
import me.fixeddev.commandflow.translator.DefaultTranslator;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class CommandLoader implements Loader {

    @Inject
    private Inferius plugin;
    @Inject
    private InferiusTranslationProvider inferiusTranslationProvider;
    @Inject
    private LanguagePartFactory languagePartFactory;

    @Inject
    private ChatCommand chatCommand;
    @Inject
    private SetSpawnCommand setSpawnCommand;
    @Inject
    private SpawnCommand spawnCommand;
    @Inject
    private WarpCommand warpCommand;
    @Inject
    private PrivateMessageCommand privateMessageCommand;
    @Inject
    private ReplyPrivateMessageCommand replyPrivateMessageCommand;

    private void register(AnnotatedCommandTreeBuilder builder, CommandManager manager, CommandClass... commandClasses) {
        for (CommandClass commandClass : commandClasses) {
            manager.registerCommands(builder.fromClass(commandClass));
        }
    }

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        partInjector.bindFactory(new Key(String.class, Language.class), languagePartFactory);

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        CommandManager commandManager = new BukkitCommandManager(new SimpleCommandManager(new BukkitAuthorizer()), plugin.getName());
        commandManager.setTranslator(new DefaultTranslator(inferiusTranslationProvider));

        register(
                builder,
                commandManager,
                chatCommand,
                setSpawnCommand,
                spawnCommand,
                warpCommand,
                privateMessageCommand,
                replyPrivateMessageCommand
        );
    }
}
