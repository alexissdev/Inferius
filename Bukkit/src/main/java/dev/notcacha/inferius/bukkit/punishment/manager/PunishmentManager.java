package dev.notcacha.inferius.bukkit.punishment.manager;

import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.response.async.AsyncResponse;
import org.bukkit.entity.Player;

public interface PunishmentManager {

    /**
     * @see PunishmentManager#add(String, String, String, String, String, String, String, PunishmentType, Long)
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
     * @see PunishmentManager#add(String, String, String, String, String, String, String, PunishmentType, Long)
     */

    default void add(String id, Player issuer, Player punished, String reason, PunishmentType type, Long endTime) {
        add(
                id,
                issuer.getUniqueId().toString(),
                issuer.getName(),
                punished.getUniqueId().toString(),
                punished.getName(),
                punished.getAddress().getHostName(),
                reason,
                type,
                endTime
        );
    }

    /**
     * Create and add a {@link Punishment} to the database
     *
     * @param issuerId        id from issuer
     * @param issuerName      name from issuer
     * @param punishedId      id from punished
     * @param punishedName    name from punished
     * @param punishedAddress address from punished
     * @param reason          from {@param punished} has been ben punished
     * @param type            from {@link Punishment} from identifier punishment type
     * @param endTime         time that the {@param punished} will be punished in any case that the {@link Long} returns a '-1' means that the ban is permanent
     */

    void add(String id, String issuerId, String issuerName, String punishedId, String punishedName, String punishedAddress, String reason, PunishmentType type, Long endTime);

    /**
     * @return a {@link AsyncResponse} doing the action asynchronously
     * @see PunishmentManager#add(String, String, String, String, String, String, String, PunishmentType, Long)
     */

    AsyncResponse<Void> addAsync(String id, String issuerId, String issuerName, String punishedId, String punishedName, String punishedAddress, String reason, PunishmentType type, Long endTime);
}
