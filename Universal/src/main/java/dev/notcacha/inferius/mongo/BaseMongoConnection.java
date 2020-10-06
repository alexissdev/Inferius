package dev.notcacha.inferius.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.notcacha.inferius.mongo.credentials.Credentials;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseMongoConnection implements MongoConnection {

    private MongoClient client;
    private Morphia morphia;
    private final Credentials credentials;
    private final Map<String, Datastore> datastores;

    public BaseMongoConnection(Credentials credentials) {
        this.credentials = credentials;
        this.datastores = new HashMap<>();

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

    public void registerDatastore(String name) {
        datastores.put(name, this.morphia.createDatastore(this.client, name));
    }

    public void registerDatastore(String name, Datastore datastore) {
        datastores.put(name, datastore);
    }

    public Datastore registerAndGetDatastore(String name) {
        Datastore datastore = this.datastores.get(name);

        if (datastore == null) {
            datastore = this.morphia.createDatastore(this.client, name);

            registerDatastore(name, datastore);
        }

        return datastore;
    }

    public Datastore getDatastore(String name) {
        Datastore datastore = this.datastores.get(name);

        if (datastore == null) {
            datastore = registerAndGetDatastore(name);
        }

        return datastore;
    }

    public boolean deleteDatastore(String name) {
        return datastores.remove(name) != null;
    }

    public Morphia getMorphia() {
        return this.morphia;
    }

    @Override
    public void close() {
        this.datastores.clear();
        this.client.close();
    }

}