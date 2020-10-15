package dev.notcacha.inferius.bukkit.utils;

import org.bukkit.ChatColor;

public class ColorApplier {

    public static String apply(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
