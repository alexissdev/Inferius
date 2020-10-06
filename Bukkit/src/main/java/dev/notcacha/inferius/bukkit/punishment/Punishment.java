package dev.notcacha.inferius.bukkit.punishment;

import dev.notcacha.inferius.buildable.Buildable;
import dev.notcacha.inferius.bukkit.punishment.issuer.Issuer;
import dev.notcacha.inferius.bukkit.punishment.punished.Punished;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.model.Model;

public interface Punishment extends Model {

    /**
     * @return default reason from punishment
     */

    String DEFAULT_REASON = "None";

    /**
     * @return an object {@link Issuer} with all the information of the issuer
     */

    Issuer getIssuer();

    /**
     * @return an object {@link Punished} with all the punished information
     */

    Punished getPunished();

    /**
     * @return reason from punishment has been set
     */

    String getReason();

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

    default boolean isPermanent() {
        return getEndTime() == -1;
    }

    interface Builder extends Buildable<Punishment> {

        /**
         * Set {@link Issuer} from {@link Punishment}
         */

        Builder setIssuer(Issuer issuer);

        /**
         * Set {@link Punished} from {@link Punishment}
         */

        Builder setPunished(Punished punished);

        /**
         * Set reason from {@link Punishment}
         *
         * @param reason has been set
         */

        Builder setReason(String reason);

        /**
         * Set type from {@link Punishment}, {@param type} has been set
         */

        Builder setType(PunishmentType type);

        /**
         * Set end time from {@link Punishment}, {@param endTime} has been set
         */

        Builder setEndTime(long endTime);


    }

    /**
     * @return an object {@link Builder} to build an object {@link Punishment}
     */

    static Builder builder(String id) {
        return new InferiusPunishment.Builder(id);
    }

}
