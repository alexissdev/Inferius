package dev.notcacha.inferius.model.delete;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.mongo.datastore.DatastoreManager;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;

import java.util.Set;

@Singleton
public class MongoModelDelete<T extends Model> implements ModelDelete<T> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private DatastoreManager datastoreManager;

    private final String databaseName;

    public MongoModelDelete(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public void deleteSync(T object) {
        datastoreManager.getDatastore(databaseName).delete(object);
    }

    @Override
    public void deleteSync(Set<T> objects) {
        objects.forEach(this::deleteSync);
    }

    @Override
    public AsyncResponse<Void> deleteAsync(T object) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.deleteSync(object);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<T> objects) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.deleteSync(objects);
            return null;
        }));
    }
}
