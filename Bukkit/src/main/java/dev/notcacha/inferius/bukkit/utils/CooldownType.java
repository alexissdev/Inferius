package dev.notcacha.inferius.bukkit.utils;

public enum CooldownType {

    CHAT_DELAY("chat_delay_%id%");

    private final String id;

    CooldownType(String id) {
        this.id = id;
    }

    public String getAndReplaceId(String id) {
        return this.id.replace("%id%", id);
    }

    public String getId() {
        return this.id;
    }
}
