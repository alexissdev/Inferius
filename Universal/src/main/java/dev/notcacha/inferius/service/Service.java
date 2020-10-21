package dev.notcacha.inferius.service;

public interface Service {

    /**
     * Start service
     */

    void start();

    /**
     * Stop service
     */

    default void stop() {
        // this method is for the classes to abstract it if necessary
    }
}
