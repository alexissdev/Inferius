package dev.notcacha.inferius.buildable;

/**
 * @param <T> represents the object to be constructed
 */

public interface Buildable<T> {

    /**
     * @return an object {@link T} that will be built
     */

    T build();
}
