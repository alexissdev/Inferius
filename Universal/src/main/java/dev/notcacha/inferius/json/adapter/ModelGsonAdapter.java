package dev.notcacha.inferius.json.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.model.serialize.SerializableModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModelGsonAdapter<T extends Model> implements GsonAdapter<T> {

    @Override
    public JsonElement serialize(T model, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        try {
            Map<String, Object> serialized = SerializableModel.serialize(model);

            for (String key : serialized.keySet()) {
                object.addProperty(key, String.valueOf(serialized.get(key)));
            }

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException("An error occurred when trying to serialize the model with the id " + model.getId() , e);
        }

        return object;
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = (JsonObject) jsonElement;

        Map<String, Object> objects = new LinkedHashMap<>();

        for (Map.Entry<String, JsonElement> element : object.entrySet()) {
            objects.put(element.getKey(), element.getValue());
        }

        try {
            return SerializableModel.deserialize(objects);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("An error occurred when trying to deserialize the model", e);
        }
    }
}
