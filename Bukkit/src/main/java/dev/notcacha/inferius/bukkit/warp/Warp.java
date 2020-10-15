package dev.notcacha.inferius.bukkit.warp;

import dev.notcacha.inferius.buildable.Buildable;
import dev.notcacha.inferius.model.Model;
import org.bukkit.Location;

public interface Warp extends Model {

    /**
     * @return location from warp
     */

    Location getLocation();

    /**
     * @return permission from using warp
     */

    String getPermission();

    interface Builder extends Buildable<Warp> {

        /**
         * Set location from warp
         *
         * @param location has been set
         */

        Builder setLocation(Location location);

        /**
         * Set permission from warp
         *
         * @param permission has been set
         */

        Builder setPermission(String permission);

    }

    /**
     * @return {@link Builder} from create {@link Warp} object
     */

    static Builder builder(String id) {
        return new InferiusWarp.Builder(id);
    }
}
