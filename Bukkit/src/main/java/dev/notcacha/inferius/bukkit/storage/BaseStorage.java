package dev.notcacha.inferius.bukkit.storage;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.model.delete.ModelDelete;
import dev.notcacha.inferius.model.find.ModelFind;
import dev.notcacha.inferius.model.save.ModelSave;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.storage.Storage;

import java.util.Optional;
import java.util.Set;

@Singleton
public class BaseStorage<T extends Model> implements Storage<T> {

    @Inject
    private ModelFind<T> modelFind;
    @Inject
    private ModelSave<T> modelSave;
    @Inject
    private ModelDelete<T> modelDelete;

    @Override
    public Optional<T> findOneSync(String id) {
        return this.modelFind.findOneSync(id);
    }

    @Override
    public Set<T> findSync(Set<String> ids) {
        return this.modelFind.findSync(ids);
    }

    @Override
    public AsyncResponse<T> findOneAsync(String id) {
        return this.modelFind.findOneAsync(id);
    }

    @Override
    public AsyncResponse<Set<T>> findAsync(Set<String> ids) {
        return this.modelFind.findAsync(ids);
    }

    @Override
    public void saveSync(T object) {
        this.modelSave.saveSync(object);
    }

    @Override
    public void saveSync(Set<T> objects) {
        this.modelSave.saveSync(objects);
    }

    @Override
    public AsyncResponse<Void> saveAsync(T object) {
        return this.modelSave.saveAsync(object);
    }

    @Override
    public AsyncResponse<Void> saveAsync(Set<T> objects) {
        return this.modelSave.saveAsync(objects);
    }

    @Override
    public void deleteSync(T object) {
        this.modelDelete.deleteSync(object);
    }

    @Override
    public void deleteSync(Set<T> objects) {
        this.modelDelete.deleteSync(objects);
    }

    @Override
    public AsyncResponse<Void> deleteAsync(T object) {
        return this.modelDelete.deleteAsync(object);
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<T> objects) {
        return this.modelDelete.deleteAsync(objects);
    }

}
