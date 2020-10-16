package dev.notcacha.inferius.mongo.datastore;

import dev.morphia.Datastore;

public interface DatastoreManager {

    /**
     * Create and register datastore from database
     *
     * @param name from datastore
     */

    void registerDatastore(String name);

    /**
     * Register datastore from database
     *
     * @param name      from datastore
     * @param datastore has been set
     */

    void registerDatastore(String name, Datastore datastore);

    /**
     * Register and get datastore
     *
     * @param name from datastore
     */

    Datastore registerAndGetDatastore(String name);

    /**
     * Get datastore
     *
     * @param name from datastore
     */

    Datastore getDatastore(String name);

    /**
     * Delete datastore
     *
     * @param name from datastore
     */

    boolean deleteDatastore(String name);

}
