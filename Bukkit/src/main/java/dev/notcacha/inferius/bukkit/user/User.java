package dev.notcacha.inferius.bukkit.user;

import dev.notcacha.inferius.buildable.Buildable;
import dev.notcacha.inferius.model.Model;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface User extends Model {

    /**
     * @return minecraft id from this {@link User}
     * */

    UUID getMinecraftId();

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
     * @return the name of the last person who sent you a message or from whom you received a message
     */

    Optional<String> getLastAuthorPrivateMessage();

    /**
     * Set last author name has been send message
     *
     * @param author name has been set
     */

    void setLastAuthorPrivateMessage(String author);


    /**
     * @return {@link Player} using {@code #getId()}
     */

    default Player getBukkitPlayerById() {
        return Bukkit.getPlayer(UUID.fromString(getId()));
    }

    /**
     * @return {@link Player} using {@see User#getName()}
     */

    default Player getBukkitPlayerByName() {
        return Bukkit.getPlayer(getName());
    }

    interface Builder extends Buildable<User> {

        /**
         * Set name from user, @param name has been set
         */

        Builder setName(String name);

        /**
         * @see User#setLanguage(String)
         */

        Builder setLanguage(String language);

    }

    /**
     * @return an instance of {@link Builder} to be able to create an {@link User} object and set its properties
     */

    static Builder builder(UUID minecraftId) {
        return new InferiusUser.Builder(minecraftId);
    }

    /**
     * @return database name from {@link User}'s )
     * */

    String DATABASE = "users";
}
