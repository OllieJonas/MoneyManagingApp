package me.csed2.moneymanager.utils;

import com.google.gson.internal.Primitives;
import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.ui.model.StageMenu;

import java.util.HashMap;

/**
 * This class contains some utility methods around classes.
 *
 * The most notable of which is the getResultFromObject() method which is used by the StageMenu {@link StageMenu} to
 * convert the object that has been converted from a string by the StringReaderFactory {@link StringReaderFactory} into
 * the correct class type.
 */
@SuppressWarnings("unused")
public class ClassUtils {

    /**
     * A HashMap which maps the wrapped versions of the basic classes into their primitive forms.
     */
    private static HashMap<Class<?>, Class<?>> unwrapped = new HashMap<>();

    /**
     * Automatically add to this HashMap when called.
     */
    static {
        unwrapped.put(Integer.class, int.class);
        unwrapped.put(Double.class, double.class);
        unwrapped.put(Boolean.class, boolean.class);
        unwrapped.put(Float.class, float.class);
        unwrapped.put(Long.class, long.class);
    }

    /**
     * Accesses the unwrapped HashMap to convert the wrapped object into its primitive counterpart.
     *
     * @param clazz The wrapped class you'd like to convert
     *
     * @return The primitive counterpart
     */
    public static Class<?> unwrap(Class<?> clazz) {
        return unwrapped.get(clazz);
    }

    /**
     * Utility method that performs both the StringReaderFactory function of converting a string into an object, and the
     * conversion of this back into the correct form.
     *
     * @param text The text to convert into the object
     * @param clazz The class you'd like to convert to
     * @param <T> The subclass of the Object that is given by the clazz
     *
     * @return The object converted into the appropriate form
     */
    public static <T> T getResultFromString(String text, Class<T> clazz) {
        try {

            Object result = StringReaderFactory.parse(text, clazz);

            return cast(result, clazz);

        } catch (InvalidTypeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Converts an object into it's appropriate subclass.
     *
     * @param result The object to be converted
     * @param clazz The class you'd like to convert it to
     * @param <T> The return type of this object
     * @return The converted object type; if this is unable to convert this then returns null
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object result, Class<T> clazz) {
        if (Primitives.wrap(clazz).isAssignableFrom(result.getClass())) {
            return (T) result;
        } else {
            return null;
        }
    }
}
