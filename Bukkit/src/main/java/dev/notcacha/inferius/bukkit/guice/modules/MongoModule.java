package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.notcacha.inferius.bukkit.file.FileCreator;
import dev.notcacha.inferius.mongo.BaseMongoConnection;
import dev.notcacha.inferius.mongo.MongoConnection;
import dev.notcacha.inferius.mongo.credentials.Credentials;
import dev.notcacha.inferius.mongo.datastore.BaseDatastoreManager;
import dev.notcacha.inferius.mongo.datastore.DatastoreManager;

public class MongoModule extends AbstractModule {

    private final FileCreator config;

    public MongoModule(FileCreator config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        this.bind(MongoConnection.class).toProvider(
                () ->
                        new BaseMongoConnection(
                                new Credentials(
                                        config.getString("mongo.host"),
                                        config.getInt("mongo.port"),
                                        config.getString("mongo.username"),
                                        config.getString("mongo.password"))
                        )).in(Scopes.SINGLETON);

        this.bind(DatastoreManager.class).to(BaseDatastoreManager.class);
    }
}
