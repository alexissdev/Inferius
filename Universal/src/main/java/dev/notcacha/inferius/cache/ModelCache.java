package dev.notcacha.inferius.cache;

import com.google.inject.Singleton;
import dev.notcacha.inferius.model.Model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class ModelCache<K, V extends Model> implements Cache<K, V> {

    private final Map<K, V> modelMap;

    public ModelCache() {
        this.modelMap = new ConcurrentHashMap<>();
    }

    @Override
    public Map<K, V> get() {
        return this.modelMap;
    }
}
