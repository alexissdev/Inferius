package dev.notcacha.inferius.bukkit.punishment.type;

public enum PunishmentType {

    BAN("banned"),
    TEMP_BAN("temp_banned"),
    KICK("kicked"),
    MUTE("muted");

    private final String id;

    PunishmentType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
