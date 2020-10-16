package dev.notcacha.inferius.model.find;

import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;

import java.util.Optional;
import java.util.Set;

public interface ModelFind<T extends Model> {

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

}
