package dev.notcacha.inferius.bukkit.punishment.issuer;

public class InferiusIssuer implements Issuer {

    private final String id;
    private final String name;

    InferiusIssuer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
