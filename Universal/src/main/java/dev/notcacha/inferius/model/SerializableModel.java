package dev.notcacha.inferius.model;

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
 * @see SerializableModel#deserialize(Class, Map)
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

        for (Method method : model.getClass().getDeclaredMethods()) {
            if (!method.getName().startsWith(get)) {
                continue;
            }

            String name = method.getName().substring(get.length());
            serialize.put(name, method.invoke(model));
        }

        return serialize;
    }

    /**
     * Deserialize a map and create an object
     *
     * @param clazz {@link Class} of the object to be deserialized
     * @param map   attributes that will be set to the deserialized object
     * @return a new object {@link T} with the characteristics set in the {@param map}
     */

    static <T extends Model> T deserialize(Class<T> clazz, Map<String, Object> map) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

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
