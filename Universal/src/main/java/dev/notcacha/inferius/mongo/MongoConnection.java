package dev.notcacha.inferius.mongo;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.io.Closeable;

public interface MongoConnection extends Closeable {

    /**
     * Create the connection to the database.
     */

    void open();

    /**
     * @return the connection to the database that the interface has been established
     */

    MongoClient get();

    /**
     * @return {@link Morphia} instance
     */

    Morphia getMorphia();

}
