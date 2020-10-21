package dev.notcacha.inferius.model.serialize;

import dev.notcacha.inferius.model.Model;
import dev.notcacha.inferius.model.serialize.annotation.Deserialize;
import dev.notcacha.inferius.model.serialize.annotation.SerializeIgnore;
import dev.notcacha.inferius.model.serialize.annotation.SerializeProperty;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides methods to serialize objects and deserialize them
 *
 * @see SerializableModel#serialize(Model)
 * @see SerializableModel#deserialize(Map)
 */

public interface SerializableModel {

    /**
     * Serialize an object in the format of a {@link Map}
     *
     * @param model which will be serialized in the {@link Map}
     * @return a {@link Map} with the object {@param model} serialized inside
     */

    static <T extends Model> Map<String, Object> serialize(T model) throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> serialize = new HashMap<>();

        String get = "get";

        Class<?> clazz = model.getClass();
        Deserialize deserialize = clazz.getAnnotation(Deserialize.class);

        if (deserialize != null) {
            Class<?> deserializeAnnotationClass = deserialize.value();
            Deserialize deserializeClass = deserializeAnnotationClass.getAnnotation(Deserialize.class);

            if (deserializeClass != null) {
                if (!deserializeClass.value().equals(deserializeAnnotationClass)) {
                    throw new IllegalArgumentException("Classes between '@Serialize' and '@Deserialize' annotations do not match");
                }

                serialize.put("deserialize_class", deserializeAnnotationClass);
            }
        }
        if (!serialize.containsKey("deserialize_class")) {
            serialize.put("deserialize_class", clazz);
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getReturnType() == void.class) {
                continue;
            }

            if (!method.getName().startsWith(get)) {
                continue;
            }

            if (!method.isAnnotationPresent(SerializeIgnore.class)) {
                continue;
            }

            SerializeProperty property = method.getAnnotation(SerializeProperty.class);

            String name = (property != null) ? property.value() : method.getName().substring(get.length()).toLowerCase();

            serialize.put(name, method.invoke(model));
        }

        return serialize;
    }

    /**
     * Deserialize a map and create an object
     *
     * @param map attributes that will be set to the deserialized object
     * @return a new object {@link T} with the characteristics set in the {@param map}
     */

    static <T extends Model> T deserialize(Map<String, Object> map) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<T> clazz = (Class<T>) map.get("deserialize_class");

        map.remove("deserialize_class");

        List<Class<?>> classList = new ArrayList<>();

        for (Object o : map.values()) {
            classList.add(o.getClass());
        }

        Class<?>[] classes = new Class[classList.size()];
        classes = classList.toArray(classes);

        Constructor<T> constructor = clazz.getDeclaredConstructor(classes);

        Object[] objects = map.values().toArray();

        return constructor.newInstance(objects);
    }
}
