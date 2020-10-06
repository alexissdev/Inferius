package dev.notcacha.inferius.bukkit.punishment;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import dev.notcacha.inferius.bukkit.exception.BuildException;
import dev.notcacha.inferius.bukkit.punishment.issuer.Issuer;
import dev.notcacha.inferius.bukkit.punishment.punished.Punished;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;


@Entity(value = "Punishments", noClassnameStored = true)
public class InferiusPunishment implements Punishment {

    @Id
    @Indexed(options = @IndexOptions(unique = true))
    private final String id;

    private final Issuer issuer;
    private final Punished punished;
    private final String reason;
    private final PunishmentType type;
    private final long endTime;
    private boolean active;

    /**
     * This constructor is found this way to be able to use Morphia
     */

    public InferiusPunishment() {
        this.issuer = null;
        this.punished = null;
        this.reason = null;
        this.type = null;
        this.endTime = -1;
        this.id = null;
        this.active = false;
    }

    public InferiusPunishment(String id, Issuer issuer, Punished punished, String reason,
                              PunishmentType type, long endTime, boolean active) {
        this.id = id;
        this.issuer = issuer;
        this.punished = punished;
        this.reason = reason;
        this.type = type;
        this.endTime = endTime;
        this.active = active;
    }

    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public Issuer getIssuer() {
        return this.issuer;
    }

    @Override
    public Punished getPunished() {
        return this.punished;
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

        private Issuer issuer;
        private Punished punished;
        private String reason;
        private PunishmentType type;
        private long endTime;

        public Builder(String id) {
            this.id = id;
            this.issuer = null;
            this.punished = null;
            this.reason = null;
            this.type = null;
            this.endTime = -1;
        }


        @Override
        public Punishment.Builder setIssuer(Issuer issuer) {
            this.issuer = issuer;
            return this;
        }

        @Override
        public Punishment.Builder setPunished(Punished punished) {
            this.punished = punished;
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
            if (issuer == null || punished == null) {
                throw new BuildException(
                        "Please check the instances of 'issuer' and 'punished' that some are returning a null value!"
                );
            }

            return new InferiusPunishment(
                    id,
                    issuer,
                    punished,
                    reason,
                    type,
                    endTime,
                    true
                    );
        }
    }
}
