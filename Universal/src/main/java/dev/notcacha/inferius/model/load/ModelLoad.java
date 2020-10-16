package dev.notcacha.inferius.model.load;

import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.response.async.AsyncResponse;

public interface ModelLoad<T extends Model> {

    /**
     * Load an object {@param T} using {@param id} as identifier to get the object
     */


    void loadSync(String id);

    /**
     * Load an object {@param T} asynchronously, using {@param id} as an identifier to get the object
     */

    AsyncResponse<Void> loadAsync(String id);
}
