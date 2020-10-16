package dev.notcacha.inferius.model.save;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.json.JsonFileCreator;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@Singleton
public class JsonModelSave<T extends Model> implements ModelSave<T> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private Gson gson;

    private final File folder;

    public JsonModelSave(File folder) {
        this.folder = folder;
    }

    @Override
    public void saveSync(T model) {
        JsonFileCreator file = JsonFileCreator.create(folder, model.getId() + ".json");

        try {
            file.writeJson(gson.toJson(model));
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred while trying to write the file", e);
        }
    }

    @Override
    public void saveSync(Set<T> models) {
        for (T model : models) {
            saveSync(model);
        }
    }

    @Override
    public AsyncResponse<Void> saveAsync(T model) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            saveSync(model);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> saveAsync(Set<T> models) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            saveSync(models);
            return null;
        }));
    }
}
