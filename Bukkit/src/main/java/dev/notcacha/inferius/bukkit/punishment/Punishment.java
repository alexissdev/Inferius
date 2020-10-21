package dev.notcacha.inferius.bukkit.punishment;

import dev.notcacha.inferius.buildable.Buildable;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.model.serialize.annotation.Deserialize;
import dev.notcacha.inferius.model.serialize.annotation.SerializeIgnore;
import dev.notcacha.inferius.model.serialize.annotation.SerializeProperty;

import java.net.InetSocketAddress;

@Deserialize(InferiusPunishment.class)
public interface Punishment extends Model {

    @SerializeProperty("_id")
    String getId();

    /**
     * @return default reason from punishment
     */

    String DEFAULT_REASON = "NONE";

    /**
     * @return id from issuer
     */

    String getIssuerId();

    /**
     * @return name from Issuer
     */

    String getIssuerName();

    /**
     * @return id from Punished
     */

    String getPunishedId();

    /**
     * @return name from Punished
     */

    String getPunishedName();

    /**
     * @return address from Punished in {@link InetSocketAddress} format
     */

    String getPunishedAddress();

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

    @SerializeIgnore
    default boolean isPermanent() {
        return getEndTime() == -1;
    }

    interface Builder extends Buildable<Punishment> {


        /**
         * Set id from issuer, {@param id} has been set
         */

        Builder setIssuerId(String id);

        /**
         * Set name from issuer, {@param name} has been set
         */

        Builder setIssuerName(String name);

        /**
         * Set id from punished, {@param id} has been set.
         */

        Builder setPunishedId(String id);

        /**
         * Set name from punished, {@param name} has been set.
         */

        Builder setPunishedName(String name);

        /**
         * Set address from punished, {@param address} has been set.
         */

        Builder setPunishedAddress(String address);

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

    /**
     * @return database name from {@link Punishment}'s)
     */

    String DATABASE = "punishments";
}
