package dev.notcacha.inferius.bukkit.spawn;

import com.google.inject.Singleton;
import dev.notcacha.inferius.manageable.ObjectManageable;
import org.bukkit.Location;

import java.util.Optional;

@Singleton
public class SpawnManageable implements ObjectManageable<Location> {

    private Location location;

    public SpawnManageable() {
        this.location = null;
    }

    @Override
    public Optional<Location> get() {
        return Optional.ofNullable(this.location);
    }

    @Override
    public void set(Location location) {
        this.location = location;
    }
}
