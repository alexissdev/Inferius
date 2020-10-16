package dev.notcacha.inferius.bukkit.adapter;

import com.google.gson.*;
import dev.notcacha.inferius.bukkit.user.User;
import dev.notcacha.inferius.json.adapter.GsonAdapter;

import java.lang.reflect.Type;
import java.util.UUID;

public class UserGsonAdapter implements GsonAdapter<User> {

    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("_id", src.getId());
        object.addProperty("name", src.getName());
        object.addProperty("language", src.getLanguage());

        return object;
    }

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        return User.builder(
                UUID.fromString(object.get("_id").getAsString())
        )
                .setName(object.get("name").getAsString())
                .setLanguage(object.get("language").getAsString())
                .build();
    }

}
