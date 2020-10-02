package dev.notcacha.inferius.storage;

import dev.notcacha.inferius.model.Model;

import java.util.Optional;

/**
 * This class is responsible for providing the disembodied methods to handle data
 *
 * @param <T> object has been used in database
 */

public interface Storage<T extends Model> {

    /**
     * @param id from object has been get
     * @return an object already created and obtained from some database or from the lake where the class is implemented
     */

    Optional<T> find(String id);

    /**
     * Load {@link T} using {@param id} from find object
     */

    void load(String id);

    /**
     * Load all {@link T} objects from storage
     */

    void loadAll();

    /**
     * Check if the object exists in the database
     *
     * @param id of the object to be checked if it exists in the database
     */

    boolean exists(String id);

    /**
     * Save an object in the database or in the place where the class is implemented
     *
     * @param object has been saved in database or other!
     */

    void save(T object);

    /**
     * Save all {@link T} in storage
     */

    void saveAll();

    /**
     * Delete an object from the database or from where the class is implemented
     *
     * @param id from object has been deleted
     */

    void delete(String id);

    /**
     * Delete an object from the database or from where the class is implemented
     *
     * @param object has been deleted
     */

    default void delete(T object) {
        delete(object.getId());
    }

}
