package dev.notcacha.inferius.bukkit.punishment.punished;

import dev.notcacha.inferius.buildable.Buildable;
import dev.notcacha.inferius.model.Model;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public interface Punished extends Model {

    /**
     * @return name from Punished
     */

    String getName();

    /**
     * @return address from Punished in {@link InetSocketAddress} format
     */

    String getAddress();

    /**
     * @return a new object {@link Punished} using the parameter {@param player} to set all properties
     */

    static Punished fromPlayer(Player player) {
        return build(player.getUniqueId().toString())
                .setName(player.getName())
                .setAddress(player.getAddress().getHostName())
                .build();
    }

    interface Builder extends Buildable<Punished> {

        /**
         * Set name from {@link Punished}, {@param name} has been set.
         */

        Builder setName(String name);

        /**
         * Set address from {@link Punished}, {@param address} has been set.
         */

        Builder setAddress(String address);

    }

    /**
     * @return builder from create {@link Punished} object
     */

    static Builder build(String id) {
        return new InferiusPunished.Builder(id);
    }
}
