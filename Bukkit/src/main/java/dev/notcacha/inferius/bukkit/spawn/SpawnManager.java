package dev.notcacha.inferius.bukkit.spawn;

import org.bukkit.Location;

import java.util.Optional;

public interface SpawnManager {

    /**
     * @return spawn {@link Location} from server
     */

    Optional<Location> get();

    /**
     * Set spawn {@link Location} from server
     *
     * @param location has been set
     */

    void set(Location location);

    /**
     * Load spawn {@link Location}
     */

    void load();

    /**
     * Save spawn {@link Location}
     */

    void save();
}
