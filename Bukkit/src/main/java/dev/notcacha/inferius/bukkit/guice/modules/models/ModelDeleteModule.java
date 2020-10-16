package dev.notcacha.inferius.bukkit.guice.modules.models;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.model.delete.JsonModelDelete;
import dev.notcacha.inferius.model.delete.ModelDelete;
import dev.notcacha.inferius.model.delete.MongoModelDelete;

public class ModelDeleteModule extends AbstractModule {

    private final Inferius inferius;
    private final boolean useDatabase;

    public ModelDeleteModule(Inferius inferius, boolean useDatabase) {
        this.inferius = inferius;
        this.useDatabase = useDatabase;
    }

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<ModelDelete<User>>() {}).toProvider(() -> {
            return (!useDatabase) ? new JsonModelDelete<>(inferius.getDataFolder()) : new MongoModelDelete<>(User.DATABASE);
        });

        this.bind(new TypeLiteral<ModelDelete<Punishment>>() {}).toProvider(() -> {
            return (!useDatabase) ? new JsonModelDelete<>(inferius.getDataFolder()) : new MongoModelDelete<>(Punishment.DATABASE);
        });
    }
}
