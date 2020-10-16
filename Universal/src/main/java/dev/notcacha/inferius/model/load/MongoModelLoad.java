package dev.notcacha.inferius.model.load;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.model.find.ModelFind;
import dev.notcacha.inferius.mongo.datastore.DatastoreManager;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;

@Singleton
public class MongoModelLoad<T extends Model> implements ModelLoad<T> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private DatastoreManager datastoreManager;

    @Inject
    private Cache<String, T> cache;
    @Inject
    private ModelFind<T> modelFind;

    @Override
    public void loadSync(String id) {
        if (cache.exists(id)) {
            return;
        }

        modelFind.findOneSync(id).ifPresent(model -> cache.add(id, model));
    }

    @Override
    public AsyncResponse<Void> loadAsync(String id) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            loadSync(id);
            return null;
        }));
    }
}
