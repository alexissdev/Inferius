package dev.notcacha.inferius.model.load;

import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;

import java.util.Optional;

public interface ModelLoad<T extends Model> {

    /**
     * Load an object {@param T} using {@param id} as identifier to get the object
     */


    Optional<T> loadSync(String id);

    /**
     * Load an object {@param T} asynchronously, using {@param id} as an identifier to get the object
     */

    AsyncResponse<T> loadAsync(String id);
}
