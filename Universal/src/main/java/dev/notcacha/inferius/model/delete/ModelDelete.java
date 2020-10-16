package dev.notcacha.inferius.model.delete;

import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;

import java.util.Set;

public interface ModelDelete<T extends Model> {

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

}
