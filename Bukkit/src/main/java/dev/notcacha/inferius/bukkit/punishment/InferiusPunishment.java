package dev.notcacha.inferius.bukkit.punishment;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.model.serialize.annotation.Serialize;

@Entity(value = "Punishments", noClassnameStored = true)
@Serialize(Punishment.class)
public class InferiusPunishment implements Punishment {

    @Id
    @Indexed(options = @IndexOptions(unique = true))
    private final String id;

    private final String issuerId;
    private final String issuerName;
    private final String punishedId;
    private final String punishedName;
    private final String punishedAddress;
    private final String reason;
    private final PunishmentType type;
    private final long endTime;
    private boolean active;

    /**
     * This constructor is found this way to be able to use Morphia
     */

    public InferiusPunishment() {
        this.issuerId = null;
        this.issuerName = null;
        this.punishedId = null;
        this.punishedName = null;
        this.punishedAddress = null;
        this.reason = null;
        this.type = null;
        this.endTime = -1;
        this.id = null;
        this.active = false;
    }

    public InferiusPunishment(String id, String issuerId, String issuerName, String punishedId, String punishedName, String punishedAddress, String reason, PunishmentType type, long endTime) {
        this.id = id;
        this.issuerId = issuerId;
        this.issuerName = issuerName;
        this.punishedId = punishedId;
        this.punishedName = punishedName;
        this.punishedAddress = punishedAddress;
        this.reason = reason;
        this.type = type;
        this.endTime = endTime;
        this.active = true;
    }

    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public String getIssuerId() {
        return this.issuerId;
    }

    @Override
    public String getIssuerName() {
        return this.issuerName;
    }

    @Override
    public String getPunishedId() {
        return this.punishedId;
    }

    @Override
    public String getPunishedName() {
        return this.punishedName;
    }

    @Override
    public String getPunishedAddress() {
        return this.punishedAddress;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public PunishmentType getType() {
        return type;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public static class Builder implements Punishment.Builder {

        private final String id;

        private String issuerId;
        private String issuerName;
        private String punishedId;
        private String punishedName;
        private String punishedAddress;
        private String reason;
        private PunishmentType type;
        private long endTime;

        public Builder(String id) {
            this.id = id;
            this.issuerId = null;
            this.issuerName = null;
            this.punishedId = null;
            this.punishedName = null;
            this.punishedAddress = null;
            this.reason = null;
            this.type = null;
            this.endTime = -1;
        }


        @Override
        public Punishment.Builder setIssuerId(String id) {
            this.issuerId = id;
            return this;
        }

        @Override
        public Punishment.Builder setIssuerName(String name) {
            this.issuerName = name;
            return this;
        }

        @Override
        public Punishment.Builder setPunishedId(String id) {
            this.punishedId = id;
            return this;
        }

        @Override
        public Punishment.Builder setPunishedName(String name) {
            this.punishedName = name;
            return this;
        }

        @Override
        public Punishment.Builder setPunishedAddress(String address) {
            this.punishedAddress = address;
            return this;
        }

        @Override
        public Punishment.Builder setReason(String reason) {
            this.reason = (reason != null) ? reason : Punishment.DEFAULT_REASON;

            return this;
        }

        @Override
        public Punishment.Builder setType(PunishmentType type) {
            this.type = type;
            return this;
        }

        @Override
        public Punishment.Builder setEndTime(long endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public Punishment build() {
            return new InferiusPunishment(
                    id,
                    issuerId,
                    issuerName,
                    punishedId,
                    punishedName,
                    punishedAddress,
                    reason,
                    type,
                    endTime
                    );
        }
    }
}
