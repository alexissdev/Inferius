package dev.notcacha.inferius.bukkit.chat.formatter;

import com.google.inject.Singleton;
import dev.notcacha.inferius.formatter.Formatter;
import org.bukkit.entity.Player;

import java.util.LinkedHashSet;
import java.util.Set;

@Singleton
public class ChatFormatter implements Formatter<Player> {

    private final Set<Formatter<Player>> formatterSet;

    public ChatFormatter() {
        this.formatterSet = new LinkedHashSet<>();
    }

    protected String format(Player player, String format, String message) {
        return setPlaceholders(player, format.replace("%message%", message));
    }

    @Override
    public String format(Player holder, String text) {
        return format(holder,"%player_name%: %message%",  text);
    }

    @Override
    public String setPlaceholders(Player holder, String text) {

        for (Formatter<Player> formatter : formatterSet) {
            text = formatter.setPlaceholders(holder, text);
        }

        return text.replace("%player_name%", holder.getName());
    }

    @Override
    public Formatter<Player> merge(Formatter<Player> formatter) {
        this.formatterSet.add(formatter);
        return this;
    }
}
