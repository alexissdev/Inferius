package dev.notcacha.inferius.bukkit.warp;

import dev.notcacha.inferius.bukkit.exception.BuildException;
import org.bukkit.Location;

public class InferiusWarp implements Warp {

    private final String id;
    private final Location location;
    private final String permission;

    InferiusWarp(String id, Location location, String permission) {
        this.id = id;
        this.location = location;
        this.permission = permission;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    public static class Builder implements Warp.Builder {

        private final String id;
        private Location location;
        private String permission;

        public Builder(String id) {
            this.id = id;
            this.location = null;
            this.permission = "inferius.warp." + id;
        }

        @Override
        public Warp.Builder setLocation(Location location) {
            this.location = location;
            return this;
        }

        @Override
        public Warp.Builder setPermission(String permission) {
            this.permission = permission;
            return this;
        }

        @Override
        public Warp build() {
            if (location == null) {
                throw new BuildException(
                        "An error occurred when creating the Warp object, the location parameter returns a null value!"
                );
            }

            return new InferiusWarp(id, location, permission);
        }
    }
}
