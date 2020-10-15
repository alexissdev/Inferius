package dev.notcacha.inferius.bukkit.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.morphia.Datastore;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.user.InferiusUser;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.mongo.MongoConnection;
import dev.notcacha.inferius.response.SimpleResponse;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;
import dev.notcacha.inferius.response.state.ResponseStatus;
import dev.notcacha.inferius.storage.Storage;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Singleton
public class UserStorage implements Storage<User> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private Inferius plugin;
    @Inject
    private MongoConnection mongoConnection;
    @Inject
    private Cache<String, User> userCache;

    @Override
    public Optional<User> findOneSync(String id) {
        User user = getDatastore().find(InferiusUser.class).field("_id").equal(id).first();

        if (user == null) {
            user = User.builder(UUID.fromString(id))
                    .setName(plugin.getServer().getOfflinePlayer(UUID.fromString(id)).getName())
                    .build();
        }

        return Optional.of(user);
    }

    @Override
    public Set<User> findSync(Set<String> ids) {

        Set<User> userSet = new HashSet<>();

        ids.forEach(id -> findOneSync(id).ifPresent(userSet::add));

        return userSet;
    }

    @Override
    public AsyncResponse<User> findOneAsync(String id) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            Optional<User> response = this.findOneSync(id);

            return response.map(user -> new SimpleResponse<>(ResponseStatus.SUCCESS, user))
                    .orElseGet(() -> new SimpleResponse(ResponseStatus.ERROR, null));
        }));
    }

    @Override
    public AsyncResponse<Set<User>> findAsync(Set<String> ids) {
        return new SimpleAsyncResponse<>(this.executorService.submit(
                () -> new SimpleResponse<>(ResponseStatus.SUCCESS, this.findSync(ids)))
        );
    }

    @Override
    public void saveSync(User object) {
        getDatastore().save(object);
    }

    @Override
    public void saveSync(Set<User> userSet) {
        userSet.forEach(this::saveSync);
    }

    @Override
    public AsyncResponse<Void> saveAsync(User user) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.saveSync(user);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> saveAsync(Set<User> userSet) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.saveSync(userSet);
            return null;
        }));
    }

    @Override
    public void deleteSync(User user) {
        getDatastore().delete(user);
    }

    @Override
    public void deleteSync(Set<User> userSet) {
        userSet.forEach(this::deleteSync);
    }

    @Override
    public AsyncResponse<Void> deleteAsync(User user) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.deleteSync(user);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<User> userSet) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.deleteSync(userSet);
            return null;
        }));
    }

    @Override
    public void load(String id) {
        if (userCache.exists(id)) {
            return;
        }

        findOneSync(id).ifPresent(user -> userCache.add(id, user));
    }

    @Override
    public void loadAsync(String id) {
        if (userCache.exists(id)) {
            return;
        }

        findOneAsync(id).callback(optionalUser -> optionalUser.getResponse().ifPresent(user -> userCache.add(id, user)));
    }

    private Datastore getDatastore() {
        return mongoConnection.getDatastore("users");
    }
}
