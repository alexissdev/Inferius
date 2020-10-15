package dev.notcacha.inferius.bukkit.placeholder;

import dev.notcacha.languagelib.placeholder.PlaceholderApplier;
import org.bukkit.Location;

public class LocationPlaceholderApplier implements PlaceholderApplier {

    @Override
    public <T> String apply(T holder, String text) {
        Location location = (Location) holder;

        return text
                .replace("%x%", String.valueOf(location.getX()))
                .replace("%y%", String.valueOf(location.getY()))
                .replace("%z%", String.valueOf(location.getZ()))
                .replace("%yaw%", String.valueOf(location.getYaw()))
                .replace("%pitch%", String.valueOf(location.getPitch()))
                .replace("%world%", String.valueOf(location.getWorld().getName()));
    }
}
