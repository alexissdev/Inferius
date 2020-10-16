package dev.notcacha.inferius.bukkit.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import dev.notcacha.inferius.bukkit.utils.LocationUtils;
import dev.notcacha.inferius.bukkit.warp.Warp;
import dev.notcacha.inferius.json.adapter.GsonAdapter;

import java.lang.reflect.Type;

public class WarpGsonAdapter implements GsonAdapter<Warp> {

    @Override
    public JsonElement serialize(Warp src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject object = new JsonObject();

        object.addProperty("_id", src.getId());
        object.addProperty("location", LocationUtils.toString(src.getLocation()));
        object.addProperty("permission", src.getPermission());

        return object;
    }

    @Override
    public Warp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        return Warp.builder(object.get("_id").getAsString())
                .setLocation(LocationUtils.fromString(object.get("location").getAsString()))
                .setPermission(object.get("permission").getAsString())
                .build();
    }
}
