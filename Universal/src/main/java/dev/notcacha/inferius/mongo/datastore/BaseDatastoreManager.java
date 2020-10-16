package dev.notcacha.inferius.mongo.datastore;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.morphia.Datastore;
import dev.notcacha.inferius.mongo.MongoConnection;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class BaseDatastoreManager implements DatastoreManager {

    @Inject
    private MongoConnection mongoConnection;
    
    private final Map<String, Datastore> datastores;
    
    public BaseDatastoreManager() {
        this.datastores = new HashMap<>();
    }

    @Override
    public void registerDatastore(String name) {
        datastores.put(name, mongoConnection.getMorphia().createDatastore(mongoConnection.get(), name));
    }

    @Override
    public void registerDatastore(String name, Datastore datastore) {
        datastores.put(name, datastore);
    }

    @Override
    public Datastore registerAndGetDatastore(String name) {
        Datastore datastore = this.datastores.get(name);

        if (datastore == null) {
            datastore = mongoConnection.getMorphia().createDatastore(mongoConnection.get(), name);

            registerDatastore(name, datastore);
        }

        return datastore;
    }

    @Override
    public Datastore getDatastore(String name) {
        Datastore datastore = this.datastores.get(name);

        if (datastore == null) {
            datastore = registerAndGetDatastore(name);
        }

        return datastore;
    }

    @Override
    public boolean deleteDatastore(String name) {
        return datastores.remove(name) != null;
    }
}
