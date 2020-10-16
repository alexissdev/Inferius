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

    public static class Builder implements Issuer.Builder {

        private final String id;
        private String name;

        public Builder(String id) {
            this.id = id;
            this.name = null;
        }

        @Override
        public Issuer.Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Issuer build() {
            return new InferiusIssuer(id, name);
        }
    }
}
