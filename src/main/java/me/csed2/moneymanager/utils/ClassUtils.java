package me.csed2.moneymanager.utils;

import com.google.gson.internal.Primitives;
import me.csed2.moneymanager.exceptions.InvalidTypeException;

public class ClassUtils {

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
