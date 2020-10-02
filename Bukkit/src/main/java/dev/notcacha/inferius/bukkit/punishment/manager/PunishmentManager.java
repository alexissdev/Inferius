package dev.notcacha.inferius.bukkit.punishment.manager;

import dev.notcacha.inferius.bukkit.punishment.Punishment;

public interface PunishmentManager {

    /**
     * @return an object {@link Punishment} using reference {@param id} to get this punishment
     */

    Punishment getPunishment(String id);

    /**
     * Add an {@link Punishment} to the database
     *
     * @param punishment will be added to the database
     */

    void addPunishment(Punishment punishment);

    /**
     * Remove a {@link Punishment} from the database
     *
     * @param id reference to get the {@link Punishment} and delete it from the database
     */

    void removePunishment(String id);

}
