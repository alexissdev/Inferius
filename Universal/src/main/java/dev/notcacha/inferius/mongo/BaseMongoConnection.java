package dev.notcacha.inferius.mongo;

import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.notcacha.inferius.mongo.credentials.Credentials;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class BaseMongoConnection implements MongoConnection {

    private MongoClient client;
    private Morphia morphia;
    private final Credentials credentials;

    public BaseMongoConnection(Credentials credentials) {
        this.credentials = credentials;

        this.open();
    }

    @Override
    public void open() {
        client = new MongoClient(
                new ServerAddress(credentials.getHost(), credentials.getPort()),
                Collections.singletonList(MongoCredential.createCredential(credentials.getUserName(),
                        "admin",
                        credentials.getPassword().toCharArray()
                ))
        );

        morphia = new Morphia();
    }

    @Override
    public MongoClient get() {
        return this.client;
    }

    public Morphia getMorphia() {
        return this.morphia;
    }

    @Override
    public void close() {
        this.client.close();
    }

}