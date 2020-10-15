package dev.notcacha.inferius.bukkit.user;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import dev.notcacha.inferius.bukkit.exception.BuildException;

import java.util.Optional;
import java.util.UUID;

@Entity(value = "Users", noClassnameStored = true)
public class InferiusUser implements User {

    @Id
    @Indexed(options = @IndexOptions(unique = true))
    private final UUID minecraftId;
    private final String name;
    private String language;
    private String lastAuthorPrivateMessage;

    /**
     * This constructor is found this way to be able to use Morphia
     */

    public InferiusUser() {
        this.minecraftId = UUID.randomUUID();
        this.name = null;
        this.language = "en";
    }

    public InferiusUser(UUID minecraftId, String name, String language) {
        this.minecraftId = minecraftId;
        this.name = name;
        this.language = language;
        this.lastAuthorPrivateMessage = null;
    }

    @Override
    public String getId() {
        return minecraftId.toString();
    }

    @Override
    public UUID getMinecraftId() {
        return minecraftId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public Optional<String> getLastAuthorPrivateMessage() {
        return Optional.of(this.lastAuthorPrivateMessage);
    }

    @Override
    public void setLastAuthorPrivateMessage(String author) {
        this.lastAuthorPrivateMessage = author;
    }

    public static class Builder implements User.Builder {

        private final UUID minecraftId;
        private String name;
        private String language;

        public Builder(UUID minecraftId) {
            this.minecraftId = minecraftId;
            this.name = null;
            this.language = "en";
        }

        @Override
        public User.Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public User.Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        @Override
        public User build() {
            if (minecraftId == null) {
                throw new BuildException(
                        "An error occurred when trying to create the User object, the id is null!"
                );
            }

            return new InferiusUser(minecraftId, name, language);
        }
    }
}
