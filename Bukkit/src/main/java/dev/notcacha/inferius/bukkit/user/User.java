package dev.notcacha.inferius.bukkit.user;

import dev.notcacha.inferius.model.Model;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface User extends Model {

    /**
     * @return name from user
     */

    String getName();

    /**
     * @return language from user
     */

    String getLanguage();

    /**
     * Set language, {@param language} has been set
     */

    void setLanguage(String language);


    /**
     * @return {@link Player} using {@code #getId()}
     */

    default Player getBukkitPlayerById() {
        return Bukkit.getPlayer(UUID.fromString(getId()));
    }

    /**
     * @return {@link Player} using {@code #getName()}
     */

    default Player getBukkitPlayerByName() {
        return Bukkit.getPlayer(getName());
    }

    interface Builder {

        /**
         * Set name from user, @param name has been set
         */

        Builder setName(String name);

        /**
         * @see User#getLanguage()
         */

        Builder setLanguage(String language);

        /**
         * @return new {@link User} created from this options!
         */

        User build();

    }

    /**
     * @return an instance of {@link Builder} to be able to create an {@link User} object and set its properties
     */

    static Builder builder(String id) {
        return new InferiusUser.Builder(id);
    }
}
