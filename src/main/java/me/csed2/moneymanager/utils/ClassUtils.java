package me.csed2.moneymanager.utils;

import com.google.gson.internal.Primitives;
import me.csed2.moneymanager.exceptions.InvalidTypeException;

import java.util.HashMap;

public class ClassUtils {

    private static HashMap<Class<?>, Class<?>> unwrapped = new HashMap<>();

    static {
        unwrapped.put(Integer.class, int.class);
        unwrapped.put(Double.class, double.class);
        unwrapped.put(Boolean.class, boolean.class);
        unwrapped.put(Float.class, float.class);
        unwrapped.put(Long.class, long.class);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Class<V> unwrap(Class<K> clazz) {
        return (Class<V>) unwrapped.get(clazz);
    }

    public static <T> T getResultFromString(String input, Class<T> clazz) {
        try {

            Object result = StringReaderFactory.parse(input, clazz);

            return getResultFromObject(result, clazz);

        } catch (InvalidTypeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getResultFromObject(Object result, Class<T> clazz) {
        if (Primitives.wrap(clazz).isAssignableFrom(result.getClass())) {
            return (T) result;
        } else {
            return null;
        }
    }
}
