package dev.notcacha.inferius.model.save;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.mongo.datastore.DatastoreManager;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;

import java.util.Set;

@Singleton
public class MongoModelSave<T extends Model> implements ModelSave<T> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private DatastoreManager datastoreManager;

    private final String databaseName;

    public MongoModelSave(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public void saveSync(T object) {
        datastoreManager.getDatastore(databaseName).save(object);
    }

    @Override
    public void saveSync(Set<T> objects) {
        objects.forEach(this::saveSync);
    }

    @Override
    public AsyncResponse<Void> saveAsync(T object) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.saveSync(object);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> saveAsync(Set<T> objects) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            this.saveSync(objects);
            return null;
        }));
    }
}
