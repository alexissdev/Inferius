package dev.notcacha.inferius.bukkit.guice.modules.models;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.model.find.JsonModelFind;
import dev.notcacha.inferius.model.find.ModelFind;
import dev.notcacha.inferius.model.find.MongoModelFind;

public class ModelFindModule extends AbstractModule {

    private final Inferius inferius;
    private final boolean useDatabase;

    public ModelFindModule(Inferius inferius, boolean useDatabase) {
        this.inferius = inferius;
        this.useDatabase = useDatabase;
    }

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<ModelFind<User>>() {}).toProvider(() -> {
            return (!useDatabase) ? new JsonModelFind<>(User.class, inferius.getDataFolder())
                    : new MongoModelFind<>(User.class, User.DATABASE);
        });

        this.bind(new TypeLiteral<ModelFind<Punishment>>() {}).toProvider(() -> {
            return (!useDatabase) ? new JsonModelFind<>(Punishment.class, inferius.getDataFolder())
                    : new MongoModelFind<>(Punishment.class, Punishment.DATABASE);
        });
    }
}
