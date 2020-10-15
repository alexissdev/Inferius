package dev.notcacha.inferius.storage;

import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;

import java.util.Optional;
import java.util.Set;

public interface Storage<T extends Model> {

    /**
     * @return {@link T} object from storage, using {@param id} as identifier to get the object
     */

    Optional<T> findOneSync(String id);

    /**
     * @return {@link T} object from storage, {@param ids} identifiers to get the objects
     */

    Set<T> findSync(Set<String> ids);

    /**
     * @param id as identifier to get the object
     * @return a {@link AsyncResponse} with the purpose of obtaining an object {@link T} asynchronously
     */

    AsyncResponse<T> findOneAsync(String id);

    /**
     * @param ids identifiers to get the objects
     * @return a {@link AsyncResponse} with the purpose of obtaining an object {@link T} asynchronously
     */

    AsyncResponse<Set<T>> findAsync(Set<String> ids);

    /**
     * Save an object in the storage, {@link T} will be the saved object,
     * {@link T#getId()} this will be the identifier of this object that will be used when it is obtained
     */

    void saveSync(T object);

    /**
     * Save multiple objects in the storage, {@link T} will be the saved objects,
     * {@link T#getId()} this will be the identifier of this object that will be used when it is obtained
     */

    void saveSync(Set<T> objects);

    /**
     * Save an object {@link T} asynchronously
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> saveAsync(T object);

    /**
     * Save multiple {@link T} objects asynchronously
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> saveAsync(Set<T> objects);

    /**
     * Remove an object {@link T} from storage, using {@param object} to identify and delete it
     */

    void deleteSync(T object);

    /**
     * Remove multiple {@link T} objects from storage, using {@param object} to identify and delete it
     */

    void deleteSync(Set<T> objects);

    /**
     * Remove an object {@link T} asynchronously using reference {@param object} to get it and remove it
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> deleteAsync(T object);

    /**
     * Remove multiple {@link T} objects asynchronously using reference {@param object} to get and remove it
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> deleteAsync(Set<T> objects);

    /**
     * Load an object {@param T} using {@param id} as identifier to get the object
     */

    void load(String id);

    /**
     * Load an object {@param T} asynchronously, using {@param id} as an identifier to get the object
     */

    void loadAsync(String id);
}
