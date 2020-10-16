package dev.notcacha.inferius.bukkit.punishment.punished;

public class InferiusPunished implements Punished {

    private final String id;
    private final String name;
    private final String address;

    InferiusPunished(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    public static class Builder implements Punished.Builder {

        private final String id;
        private String name;
        private String addres;

        public Builder(String id) {
            this.id = id;
            this.name = null;
            this.addres = null;
        }

        @Override
        public Punished.Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Punished.Builder setAddress(String address) {
            this.addres = address;
            return this;
        }

        @Override
        public Punished build() {
            return new InferiusPunished(id, name, addres);
        }
    }
}
