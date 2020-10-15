package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import dev.notcacha.inferius.bukkit.chat.formatter.ChatFormatter;
import dev.notcacha.inferius.formatter.Formatter;
import org.bukkit.entity.Player;

public class FormatterModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<Formatter<Player>>() {}).annotatedWith(Names.named("chat-formatter")).to(ChatFormatter.class);
    }
}
