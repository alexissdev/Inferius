package dev.notcacha.inferius.bukkit.adapter;

import com.google.gson.*;
import dev.notcacha.inferius.bukkit.utils.LocationUtils;
import org.bukkit.Location;

import java.lang.reflect.Type;

public class LocationAdapter implements GsonAdapter<Location> {

    @Override
    public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("location", LocationUtils.toString(src));

        return object;
    }

    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocationUtils.fromString(((JsonObject) json).get("location").getAsString());
    }
}
