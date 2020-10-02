package dev.notcacha.inferius.bukkit.storage;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.morphia.Datastore;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.punishment.InferiusPunishment;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.mongo.MongoConnection;
import dev.notcacha.inferius.storage.Storage;

import java.util.Optional;
import java.util.logging.Level;

@Singleton
public class PunishmentStorage implements Storage<Punishment> {

    @Inject
    private Inferius plugin;

    @Inject
    private MongoConnection mongoConnection;

    @Override
    public Optional<Punishment> find(String id) {
        return Optional.ofNullable(
                getDatastore().find(InferiusPunishment.class).field("_id").equal(id).first()
        );
    }

    @Override
    public void load(String id) {
        plugin.getLogger().log(Level.SEVERE, "This method is totally useless in this implementation!");
    }

    @Override
    public void loadAll() {
        plugin.getLogger().log(Level.SEVERE, "This method is totally useless in this implementation!");
    }

    @Override
    public boolean exists(String id) {
        return find(id).isPresent();
    }

    @Override
    public void save(Punishment punishment) {
        getDatastore().save(punishment);
    }

    @Override
    public void saveAll() {
        plugin.getLogger().log(Level.SEVERE, "This method is totally useless in this implementation!");
    }

    @Override
    public void delete(String id) {
        find(id).ifPresent(punishment -> getDatastore().delete(punishment));
    }

    @Override
    public void delete(Punishment punishment) {
        delete(punishment.getId());
    }

    private Datastore getDatastore() {
        return mongoConnection.getDatastore("Punishment");
    }
}
