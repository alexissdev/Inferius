package dev.notcacha.inferius.model.load;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.model.find.ModelFind;
import dev.notcacha.inferius.response.SimpleResponse;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;
import dev.notcacha.inferius.response.state.ResponseStatus;

import java.util.Optional;

@Singleton
public class SimpleModelLoad<T extends Model> implements ModelLoad<T> {

    @Inject
    private ListeningExecutorService executorService;

    @Inject
    private Cache<String, T> cache;
    @Inject
    private ModelFind<T> modelFind;

    @Override
    public Optional<T> loadSync(String id) {
        if (cache.exists(id)) {
            return cache.find(id);
        }

        Optional<T> optionalModel = modelFind.findOneSync(id);
        optionalModel.ifPresent(model -> cache.add(id, model));

        return optionalModel;
    }

    @Override
    public AsyncResponse<T> loadAsync(String id) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            Optional<T> response = loadSync(id);

            return response.map(model -> new SimpleResponse<>(ResponseStatus.SUCCESS, model))
                    .orElseGet(() -> new SimpleResponse<>(ResponseStatus.ERROR, null));
        }));
    }
}
