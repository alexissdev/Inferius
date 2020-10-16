package dev.notcacha.inferius.model.save;

import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;

import java.util.Set;

public interface ModelSave<T extends Model> {

    /**
     * Save an object in the storage, {@link T} will be the saved object,
     * {@link T#getId()} this will be the identifier of this object that will be used when it is obtained
     */

    void saveSync(T model);

    /**
     * Save multiple objects in the storage, {@link T} will be the saved objects,
     * {@link T#getId()} this will be the identifier of this object that will be used when it is obtained
     */

    void saveSync(Set<T> models);

    /**
     * Save an object {@link T} asynchronously
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> saveAsync(T model);

    /**
     * Save multiple {@link T} objects asynchronously
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> saveAsync(Set<T> models);
}
