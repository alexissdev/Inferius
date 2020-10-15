package dev.notcacha.inferius.bukkit.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.morphia.Datastore;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.punishment.InferiusPunishment;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.mongo.MongoConnection;
import dev.notcacha.inferius.response.SimpleResponse;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;
import dev.notcacha.inferius.response.state.ResponseStatus;
import dev.notcacha.inferius.storage.Storage;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;

@Singleton
public class PunishmentStorage implements Storage<Punishment> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private Inferius plugin;
    @Inject
    private MongoConnection mongoConnection;


    @Override
    public Optional<Punishment> findOneSync(String id) {
        return Optional.ofNullable(
                getDatastore().find(InferiusPunishment.class).field("_id").equal(id).first()
        );
    }

    @Override
    public Set<Punishment> findSync(Set<String> ids) {
        Set<Punishment> punishmentSet = new HashSet<>();

        ids.forEach(id -> this.findOneAsync(id).callback(response -> response.getResponse().ifPresent(punishmentSet::add)));

        return punishmentSet;
    }

    @Override
    public AsyncResponse<Punishment> findOneAsync(String id) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            Optional<Punishment> response = this.findOneSync(id);

            return response.map(punishment -> new SimpleResponse<>(ResponseStatus.SUCCESS, punishment))
                    .orElseGet(() -> new SimpleResponse(ResponseStatus.ERROR, null));
        }));
    }

    @Override
    public AsyncResponse<Set<Punishment>> findAsync(Set<String> ids) {
        return new SimpleAsyncResponse<>(this.executorService.submit(
                () -> new SimpleResponse<>(ResponseStatus.SUCCESS, this.findSync(ids)))
        );
    }

    @Override
    public void saveSync(Punishment punishment) {
        getDatastore().save(punishment);
    }

    @Override
    public void saveSync(Set<Punishment> punishmentSet) {
        punishmentSet.forEach(this::saveSync);
    }

    @Override
    public AsyncResponse<Void> saveAsync(Punishment punishment) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.saveSync(punishment);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> saveAsync(Set<Punishment> punishmentSet) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.saveSync(punishmentSet);
            return null;
        }));
    }

    @Override
    public void deleteSync(Punishment punishment) {
        getDatastore().delete(punishment);
    }

    @Override
    public void deleteSync(Set<Punishment> punishmentSet) {
        punishmentSet.forEach(this::deleteSync);
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Punishment punishment) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.deleteSync(punishment);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<Punishment> punishmentSet) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.deleteSync(punishmentSet);
            return null;
        }));
    }

    @Override
    public void load(String id) {
        plugin.getLogger().log(Level.SEVERE, "This method is totally useless in this implementation!");
    }

    @Override
    public void loadAsync(String id) {
        load(id);
    }

    private Datastore getDatastore() {
        return mongoConnection.getDatastore("punishments");
    }
}
