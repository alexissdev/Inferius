package dev.notcacha.inferius.bukkit.punishment.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.storage.Storage;

@Singleton
public class InferiusPunishmentManager implements PunishmentManager {

    @Inject
    private Storage<Punishment> punishmentStorage;


    @Override
    public Punishment getPunishment(String id) {
        return punishmentStorage.find(id).orElse(null);
    }

    @Override
    public void addPunishment(Punishment punishment) {
        punishmentStorage.save(punishment);
    }

    @Override
    public void removePunishment(String id) {
        punishmentStorage.delete(id);
    }
}
