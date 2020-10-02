package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dev.notcacha.inferius.bukkit.spawn.SpawnManageable;
import dev.notcacha.inferius.manageable.ObjectManageable;
import org.bukkit.Location;

public class ManageableModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<ObjectManageable<Location>>() {}).to(SpawnManageable.class);
    }
}
