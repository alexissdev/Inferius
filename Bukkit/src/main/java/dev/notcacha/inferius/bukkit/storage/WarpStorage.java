package dev.notcacha.inferius.bukkit.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.file.JsonFileCreator;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.cache.Cache;
import dev.notcacha.inferius.response.SimpleResponse;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;
import dev.notcacha.inferius.response.state.ResponseStatus;
import dev.notcacha.inferius.storage.Storage;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Singleton
public class WarpStorage implements Storage<Warp> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private Inferius inferius;
    @Inject
    private Cache<String, Warp> warpCache;
    @Inject
    private Gson gson;

    @Override
    public Optional<Warp> findOneSync(String id) {
        JsonFileCreator file = JsonFileCreator.create(new File(inferius.getDataFolder(), "/warps/"), id);

        return Optional.ofNullable(gson.fromJson(file.getJsonString(), Warp.class));
    }

    @Override
    public Set<Warp> findSync(Set<String> ids) {
        Set<Warp> warpSet = new HashSet<>();

        for (String id : ids) {
            findOneSync(id).ifPresent(warpSet::add);
        }

        return warpSet;
    }

    @Override
    public AsyncResponse<Warp> findOneAsync(String id) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            Optional<Warp> response = findOneSync(id);

            return response.map(warp -> new SimpleResponse<>(ResponseStatus.SUCCESS, warp))
                    .orElseGet(() -> new SimpleResponse(ResponseStatus.ERROR, null));
        }));
    }

    @Override
    public AsyncResponse<Set<Warp>> findAsync(Set<String> ids) {
        return new SimpleAsyncResponse<>(this.executorService.submit(
                () -> new SimpleResponse<>(ResponseStatus.SUCCESS, this.findSync(ids)))
        );
    }

    @Override
    public void saveSync(Warp warp) {
        JsonFileCreator file = JsonFileCreator.create(new File(inferius.getDataFolder(), "/warps/"), warp.getId());

        file.writeJson(gson.toJson(warp));
    }

    @Override
    public void saveSync(Set<Warp> warpSet) {
        for (Warp warp : warpSet) {
            saveSync(warp);
        }
    }

    @Override
    public AsyncResponse<Void> saveAsync(Warp warp) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            saveSync(warp);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> saveAsync(Set<Warp> warpSet) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            saveSync(warpSet);
            return null;
        }));
    }

    @Override
    public void deleteSync(Warp warp) {
        File file = new File(new File(inferius.getDataFolder(), "/warps/"), warp.getId() + ".json");
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void deleteSync(Set<Warp> warpSet) {
        for (Warp warp : warpSet) {
            deleteSync(warp);
        }
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Warp warp) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            deleteSync(warp);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<Warp> warpSet) {
        return new SimpleAsyncResponse<>(this.executorService.submit(() -> {
            saveSync(warpSet);
            return null;
        }));
    }

    @Override
    public void load(String id) {
        if (warpCache.exists(id)) {
            return;
        }

        findOneSync(id).ifPresent(warp -> warpCache.add(id, warp));
    }

    @Override
    public void loadAsync(String id) {
        if (warpCache.exists(id)) {
            return;
        }

        findOneAsync(id).callback(response -> response.getResponse().ifPresent(warp -> warpCache.add(id, warp)));
    }
}
