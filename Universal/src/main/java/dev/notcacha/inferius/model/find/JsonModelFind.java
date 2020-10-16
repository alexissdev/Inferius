package dev.notcacha.inferius.model.find;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.json.JsonFileCreator;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.SimpleResponse;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;
import dev.notcacha.inferius.response.state.ResponseStatus;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Singleton
public class JsonModelFind<T extends Model> implements ModelFind<T> {

    @Inject
    private ListeningExecutorService executorService;

    @Inject
    private Gson gson;

    private final Class<T> clazz;
    private final File folder;

    public JsonModelFind(Class<T> clazz, File folder) {
        this.clazz = clazz;
        this.folder = folder;
    }

    @Override
    public Optional<T> findOneSync(String id) {
        JsonFileCreator file = JsonFileCreator.create(folder, id + ".json");

        try {
            return Optional.ofNullable(gson.fromJson(file.getJsonString(), clazz));
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred while trying to get the text in JSON format", e);
        }
    }

    @Override
    public Set<T> findSync(Set<String> ids) {
        Set<T> modelSet = new HashSet<>();

        for (String id : ids) {
            findOneSync(id).ifPresent(modelSet::add);
        }

        return modelSet;
    }

    @Override
    public AsyncResponse<T> findOneAsync(String id) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            Optional<T> response = findOneSync(id);

            return response.map(punishment -> new SimpleResponse<>(ResponseStatus.SUCCESS, punishment))
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
