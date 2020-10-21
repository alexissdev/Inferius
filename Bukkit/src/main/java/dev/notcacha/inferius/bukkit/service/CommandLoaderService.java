package dev.notcacha.inferius.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.commands.ChatCommand;
import dev.notcacha.inferius.bukkit.commands.PrivateMessageCommand;
import dev.notcacha.inferius.bukkit.commands.ReplyPrivateMessageCommand;
import dev.notcacha.inferius.bukkit.commands.SetSpawnCommand;
import dev.notcacha.inferius.bukkit.commands.SpawnCommand;
import dev.notcacha.inferius.bukkit.commands.WarpCommand;
import dev.notcacha.inferius.bukkit.flow.InferiusTranslationProvider;
import dev.notcacha.inferius.service.Service;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.SimpleCommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitAuthorizer;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.command.Command;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class CommandLoaderService implements Service {

    @Inject
    private Inferius plugin;
    @Inject
    private InferiusTranslationProvider inferiusTranslationProvider;

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

    @Override
    public void start() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        CommandManager commandManager = new BukkitCommandManager(new SimpleCommandManager(new BukkitAuthorizer()), plugin.getName());
        commandManager.getTranslator().setProvider(inferiusTranslationProvider);

        List<Command> commandList = new ArrayList<>();

        commandList.addAll(builder.fromClass(chatCommand));
        commandList.addAll(builder.fromClass(setSpawnCommand));
        commandList.addAll(builder.fromClass(spawnCommand));
        commandList.addAll(builder.fromClass(warpCommand));
        commandList.addAll(builder.fromClass(privateMessageCommand));
        commandList.addAll(builder.fromClass(replyPrivateMessageCommand));

        commandManager.registerCommands(commandList);
    }
}
