package dev.notcacha.inferius.bukkit.punishment.manager;

import dev.notcacha.inferius.bukkit.punishment.issuer.Issuer;
import dev.notcacha.inferius.bukkit.punishment.punished.Punished;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import org.bukkit.entity.Player;

public interface PunishmentManager {

    /**
     * @see PunishmentManager#add(String id, Player, Player, String, PunishmentType, Long)  to get the documentation for this method
     */

    default void add(String id, Player issuer, Player punished, String reason, PunishmentType type) {
        add(
                id,
                issuer,
                punished,
                reason,
                type,
                -1L
        );
    }

    /**
     * @see PunishmentManager#add(String, Issuer, Punished, String, PunishmentType, Long) to get the documentation for this method
     */

    default void add(String id, Player issuer, Player punished, String reason, PunishmentType type, Long endTime) {
        add(
                id,
                Issuer.fromPlayer(issuer),
                Punished.fromPlayer(punished),
                reason,
                type,
                endTime
        );
    }

    /**
     * Create and add a {@link Punishment} to the database
     *
     * @param issuer   person who is doing the punishment
     * @param punished person who is being punished by {@param issuer}
     * @param reason   from {@param punished} has been ben punished
     * @param type     from {@link Punishment} from identifier punishment type
     * @param endTime  time that the {@param punished} will be punished in any case that the {@link Long} returns a '-1' means that the ban is permanent
     */
    
    void add(String id, Issuer issuer, Punished punished, String reason, PunishmentType type, Long endTime);

}
