package dev.notcacha.inferius.manageable;

import java.util.Optional;

/**
 * This class is used to handle objects in a very simple way
 *
 * @param <T> the object that will be handled by this class
 */

public interface ObjectManageable<T> {

    /**
     * @return This method returns the object that is being handled by the class that is implementing this interface
     */

    Optional<T> get();

    /**
     * Sets the object that is being handled
     *
     * @param object has been set
     */

    void set(T object);
}
