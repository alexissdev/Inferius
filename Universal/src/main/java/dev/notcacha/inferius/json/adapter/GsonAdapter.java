package dev.notcacha.inferius.json.adapter;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

/**
 * This interface is to have a kind of inheritance with the json serializer and deserializer
 *
 * @param <T> will be the object that an adaptation is created when serializing and deserializing
 */

public interface GsonAdapter<T> extends JsonSerializer<T>, JsonDeserializer<T> {
}
