package dev.notcacha.inferius.bukkit.punishment.type;

public enum PunishmentType {

    BAN("banned"),
    KICK("kicked"),
    MUTE("muted");

    private final String id;

    PunishmentType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
