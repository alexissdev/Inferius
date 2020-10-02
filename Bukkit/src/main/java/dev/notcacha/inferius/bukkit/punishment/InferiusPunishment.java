package dev.notcacha.inferius.bukkit.punishment;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;

import java.util.UUID;


@Entity(value = "Punishments", noClassnameStored = true)
public class InferiusPunishment implements Punishment {

    @Id
    @Indexed(options = @IndexOptions(unique = true))
    private final String id;

    private final UUID issuerId;
    private final String issuerName;
    private final UUID punishedId;
    private final String punishedName;
    private final String punishedIp;
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
        this.punishedIp = null;
        this.type = null;
        this.endTime = -1;
        this.id = null;
        this.active = false;
    }

    public InferiusPunishment(UUID issuerId, String issuerName, UUID punishedId,
                              String punishedName, String punishedIp, PunishmentType type, long endTime, boolean active, String id) {
        this.issuerId = issuerId;
        this.issuerName = issuerName;
        this.punishedId = punishedId;
        this.punishedName = punishedName;
        this.punishedIp = punishedIp;
        this.type = type;
        this.endTime = endTime;
        this.id = id;
        this.active = true;
    }

    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public UUID getIssuerId() {
        return issuerId;
    }

    @Override
    public String getIssuerName() {
        return issuerName;
    }

    @Override
    public UUID getPunishedId() {
        return punishedId;
    }

    @Override
    public String getPunishedName() {
        return punishedName;
    }

    @Override
    public String getPunishedIp() {
        return punishedIp;
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

        private UUID issuerId;
        private String issuerName;
        private UUID punishedId;
        private String punishedName;
        private String punishedIp;
        private PunishmentType type;
        private long endTime;

        public Builder(String id) {
            this.id = id;
            this.issuerId = null;
            this.issuerName = null;
            this.punishedId = null;
            this.punishedName = null;
            this.punishedIp = null;
            this.type = null;
            this.endTime = -1;
        }

        @Override
        public Punishment.Builder setIssuerId(UUID id) {
            this.issuerId = id;
            return this;
        }

        @Override
        public Punishment.Builder setIssuerName(String name) {
            this.issuerName = name;
            return this;
        }

        @Override
        public Punishment.Builder setPunishedId(UUID id) {
            this.punishedId = id;
            return this;
        }

        @Override
        public Punishment.Builder setPunishedName(String name) {
            this.punishedName = name;
            return this;
        }

        @Override
        public Punishment.Builder setPunishedIp(String ip) {
            this.punishedIp = ip;
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
                    issuerId,
                    issuerName,
                    punishedId,
                    punishedName,
                    punishedIp,
                    type,
                    endTime,
                    true,
                    id);
        }
    }
}
