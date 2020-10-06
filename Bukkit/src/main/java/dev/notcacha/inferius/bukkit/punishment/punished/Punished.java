package dev.notcacha.inferius.bukkit.punishment.punished;

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

    InetSocketAddress getAddress();

    /**
     * @return a new object {@link Punished} using the parameter {@param player} to set all properties
     */

    static Punished fromPlayer(Player player) {
        return new InferiusPunished(
                player.getUniqueId().toString(),
                player.getName(),
                player.getAddress()
        );
    }
}
