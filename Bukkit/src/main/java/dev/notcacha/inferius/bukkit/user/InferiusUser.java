package dev.notcacha.inferius.bukkit.user;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import dev.notcacha.inferius.bukkit.exception.BuildException;

@Entity(value = "Users", noClassnameStored = true)
public class InferiusUser implements User {

    @Id
    @Indexed(options = @IndexOptions(unique = true))
    private final String id;
    private final String name;
    private String language;

    /**
     * This constructor is found this way to be able to use Morphia
     */

    public InferiusUser() {
        this.id = null;
        this.name = null;
        this.language = null;
    }

    public InferiusUser(String id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    @Override
    public String getId() {
        return id;
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

    public static class Builder implements User.Builder {

        private final String id;
        private String name;
        private String language;

        public Builder(String id) {
            this.id = id;
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
            if (id == null) {
                throw new BuildException(
                        "An error occurred when trying to create the User object, the id is null!"
                );
            }

            return new InferiusUser(id, name, language);
        }
    }
}
