package me.csed2.moneymanager.utils;

import com.google.gson.internal.Primitives;
import lombok.experimental.UtilityClass;
import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.StageMenu;

import java.util.HashMap;

/**
 * This class contains some utility methods around classes.
 *
 * The most notable of which is the getResultFromObject() method which is used by the StageMenu {@link StageMenu} to
 * convert the object that has been converted from a string by the StringReaderFactory {@link StringParserFactory} into
 * the correct class type.
 */
@UtilityClass
public class ClassUtils {

    /**
     * Converts an object into it's appropriate subclass.
     *
     * @param result The object to be converted
     * @param clazz The class you'd like to convert it to
     * @param <T> The return type of this object
     * @return The converted object type; if this is unable to convert this then returns null
     */
    @SuppressWarnings("unchecked")
    public <T> T cast(Object result, Class<T> clazz) {
        if (Primitives.wrap(clazz).isAssignableFrom(result.getClass())) {
            return (T) result;
        } else {
            throw new InvalidTypeException(result, clazz);
        }
    }

    public boolean canCast(Object result, Class<?> clazz) {
        return Primitives.wrap(clazz).isAssignableFrom(result.getClass());
    }
}
