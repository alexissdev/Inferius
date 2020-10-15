package dev.notcacha.inferius.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {

    public static String toString(Location location) {
        return StringJoiningApplier.apply(
                location.getWorld().getName(),
                String.valueOf(location.getX()),
                String.valueOf(location.getY()),
                String.valueOf(location.getZ()),
                String.valueOf(location.getYaw()),
                String.valueOf(location.getPitch())
        );
    }

    public static Location fromString(String location) {
        String[] locationPart = location.split(";");

        return new Location(
                Bukkit.getWorld(locationPart[0]),
                Double.parseDouble(locationPart[1]),
                Double.parseDouble(locationPart[2]),
                Double.parseDouble(locationPart[3]),
                Float.parseFloat(locationPart[4]),
                Float.parseFloat(locationPart[5])
        );
    }

}
