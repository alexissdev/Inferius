package dev.notcacha.inferius.mongo;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public interface MongoConnection {

    /**
     * Create the connection to the database.
     */

    void open();

    /**
     * @return the connection to the database that the interface has been established
     */

    MongoClient get();

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

    /**
     * @return {@link Morphia} instance
     */

    Morphia getMorphia();

    /**
     * Close the connection to the database.
     */

    void close();

}
