package dev.notcacha.inferius.bukkit.guice.modules.models;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.model.save.JsonModelSave;
import dev.notcacha.inferius.model.save.ModelSave;
import dev.notcacha.inferius.model.save.MongoModelSave;

public class ModelSaveModule extends AbstractModule {

    private final Inferius inferius;
    private final boolean useDatabase;

    public ModelSaveModule(Inferius inferius, boolean useDatabase) {
        this.inferius = inferius;
        this.useDatabase = useDatabase;
    }

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<ModelSave<User>>() {}).toProvider(() -> {
            return (!useDatabase) ? new JsonModelSave<>(inferius.getDataFolder()) : new MongoModelSave<>(User.DATABASE);
        });

        this.bind(new TypeLiteral<ModelSave<Punishment>>() {}).toProvider(() -> {
            return (!useDatabase) ? new JsonModelSave<>(inferius.getDataFolder()) : new MongoModelSave<>(Punishment.DATABASE);
        });
    }
}
