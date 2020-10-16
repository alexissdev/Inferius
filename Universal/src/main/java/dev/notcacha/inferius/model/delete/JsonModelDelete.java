package dev.notcacha.inferius.model.delete;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;

import java.io.File;
import java.util.Set;

@Singleton
public class JsonModelDelete<T extends Model> implements ModelDelete<T> {

    @Inject
    private ListeningExecutorService executorService;
    private final File folder;

    public JsonModelDelete(File folder) {
        this.folder = folder;
    }

    @Override
    public void deleteSync(T model) {
        File file = new File(folder, model.getId() + ".json");
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void deleteSync(Set<T> models) {
        for (T model : models) {
            deleteSync(model);
        }
    }

    @Override
    public AsyncResponse<Void> deleteAsync(T model) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            deleteSync(model);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<T> models) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            deleteSync(models);
            return null;
        }));
    }
}
