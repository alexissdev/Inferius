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
import dev.notcacha.inferius.storage.Storage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
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
    public Optional<User> find(String id) {
        User user = getDatastore().find(InferiusUser.class).field("_id").equal(id).first();

        if (user == null) {
            user = User.builder(id)
                    .setName(plugin.getServer().getOfflinePlayer(UUID.fromString(id)).getName())
                    .build();
        }

        return Optional.of(user);
    }


    @Override
    public void load(String id) {
        if (userCache.exists(id)) {
            userCache.remove(id);
        }
        find(id).ifPresent(user -> userCache.add(id, user));
    }

    @Override
    public void loadAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            load(player.getUniqueId().toString());
        }
    }

    @Override
    public boolean exists(String id) {
        return getDatastore().find(InferiusUser.class).field("_id").equal(id).first() != null;
    }

    @Override
    public void save(User user) {
        getDatastore().save(user);

        this.userCache.remove(user.getId());
    }

    @Override
    public void saveAll() {
        userCache.get().values().forEach(this::save);
    }

    @Override
    public void delete(String id) {
        find(id).ifPresent(user -> getDatastore().delete(user));
    }

    @Override
    public void delete(User object) {
        delete(object.getId());
    }

    private Datastore getDatastore() {
        return mongoConnection.getDatastore("Users");
    }
}
