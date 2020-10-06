package dev.notcacha.inferius.bukkit.punishment.punished;

import java.net.InetSocketAddress;

public class InferiusPunished implements Punished {

    private final String id;
    private final String name;
    private final InetSocketAddress address;

    InferiusPunished(String id, String name, InetSocketAddress address) {
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
    public InetSocketAddress getAddress() {
        return this.address;
    }
}
