package dev.notcacha.inferius.bukkit.punishment;

import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.model.Model;

import java.util.UUID;

public interface Punishment extends Model {

    /**
     * @return the {@link UUID} of the punishment author
     */

    UUID getIssuerId();

    /**
     * @return the name of the punishment author
     */

    String getIssuerName();

    /**
     * @return the {@link UUID} of the author who received the punishment
     */

    UUID getPunishedId();

    /**
     * @return the name of the author who received the punishment
     */

    String getPunishedName();

    /**
     * @return the ip of the author who received the punishment
     */

    String getPunishedIp();

    /**
     * @return {@link PunishmentType} from this punishment
     */

    PunishmentType getType();

    /**
     * @return end time from this punishment
     */

    long getEndTime();

    /**
     * @return punishment is active!
     */

    boolean isActive();

    /**
     * Set active state from punishment
     */

    void setActive(boolean active);

    /**
     * @return this punishment is permanent
     */

    default boolean hasPermanent() {
        return getEndTime() == -1;
    }

    interface Builder {

        /**
         * Set issuerId, {@param id} has been set
         */

        Builder setIssuerId(UUID id);

        /**
         * Set issuerName, {@param name} has been set
         */

        Builder setIssuerName(String name);

        /**
         * Set punishedId, {@param id} has been set
         */

        Builder setPunishedId(UUID id);

        /**
         * Set punishedName, {@param name} has been set
         */

        Builder setPunishedName(String name);

        /**
         * Set punishedIp, {@param ip} has been set
         */

        Builder setPunishedIp(String ip);

        /**
         * Set type from punishment, {@param type} has been set
         */

        Builder setType(PunishmentType type);

        /**
         * Set end time from punishment, {@param endTime} has been set
         */

        Builder setEndTime(long endTime);

        /**
         * @return a new object {@link Punishment} with all previous properties set
         */

        Punishment build();


    }

    /**
     * @return an object {@link Builder} to build an object {@link Punishment}
     */

    static Builder builder(String id) {
        return new InferiusPunishment.Builder(id);
    }

}
