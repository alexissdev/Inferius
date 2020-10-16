package dev.notcacha.inferius.model.find;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.mongo.datastore.DatastoreManager;
import dev.notcacha.inferius.response.SimpleResponse;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;
import dev.notcacha.inferius.response.state.ResponseStatus;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Singleton
public class MongoModelFind<T extends Model> implements ModelFind<T> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private DatastoreManager datastoreManager;

    private final Class<T> clazz;
    private final String databaseName;

    public MongoModelFind(Class<T> clazz, String databaseName) {
        this.clazz = clazz;
        this.databaseName = databaseName;
    }


    @Override
    public Optional<T> findOneSync(String id) {
        return Optional.ofNullable(datastoreManager.getDatastore(databaseName).find(clazz).field("_id").equal(id).first());
    }

    @Override
    public Set<T> findSync(Set<String> ids) {
        Set<T> set = new HashSet<>();

        ids.forEach(id -> findOneSync(id).ifPresent(set::add));

        return set;
    }

    @Override
    public AsyncResponse<T> findOneAsync(String id) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            Optional<T> response = this.findOneSync(id);

            return response.map(object -> new SimpleResponse<>(ResponseStatus.SUCCESS, object))
                    .orElseGet(() -> new SimpleResponse(ResponseStatus.ERROR, null));
        }));
    }

    @Override
    public AsyncResponse<Set<T>> findAsync(Set<String> ids) {
        return new SimpleAsyncResponse<>(this.executorService.submit(
                () -> new SimpleResponse<>(ResponseStatus.SUCCESS, this.findSync(ids)))
        );
    }
}
