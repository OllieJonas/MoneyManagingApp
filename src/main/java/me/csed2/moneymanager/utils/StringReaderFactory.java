package me.csed2.moneymanager.utils;

import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.ui.cmdline.InputProcessor;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

/**
 * This class is responsible for turning a String into an Object.
 *
 * The InputProcessor {@link InputProcessor} primarily uses this for the StageMenu {@link StageMenu} in order to turn
 * the text that the user inputs into the correct object type.
 *
 * For some context, I (rather naively) tried just casting it to an Object and hoping that it would
 * automatically recognise the type entered. This wasn't the case, so I created this class.
 *
 * This class works by taking in the text input, and the type you'd like to cast it to, which should have been specified
 * in the Stage {@link Stage} that they're using. It then attempts to turn this string into that object using the other
 * methods below, and if unable it throws an InvalidTypeException {@link InvalidTypeException}.
 *
 * One can then use the ClassUtils {@link ClassUtils} utility to turn the object with the correct type into the
 * appropriate subclass which is then processed.
 *
 * This class follows the factory design pattern (https://refactoring.guru/design-patterns/factory-method).
 *
 * @author Ollie
 * @since 11/03/2020
 */
public class StringReaderFactory {

    /**
     * Main method for StringReaderFactory. Turns the string input an object with the appropriate subclass.
     *
     * @param text The text the user inputted
     * @param type The type you'd like to turn this text into
     *
     * @return An object with the correct type
     *
     * @throws InvalidTypeException If unable to convert the text into an object with the type
     */
    public static Object parse(String text, Class<?> type) throws InvalidTypeException {
        if (type == Integer.class) {
            return readInt(text);
        } else if (type == Double.class) {
            return readDouble(text);
        } else if (type == Long.class) {
            return readLong(text);
        } else if (type == Float.class) {
            return readFloat(text);
        } else if (type == Boolean.class) {
            return readBoolean(text);
        } else if (type == String.class) {
            return text;
        } else {
            throw new InvalidTypeException();
        }
    }

    /**
     * Attempts to turn the text into an integer.
     *
     * @param text The text
     *
     * @return An object of subtype integer
     *
     * @throws InvalidTypeException If unable to complete this conversion
     */
    private static int readInt(String text) throws InvalidTypeException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Integer.class, String.class);
        }
    }

    /**
     * Attempts to turn the text into a long.
     *
     * @param text The text
     *
     * @return An object of subtype long
     *
     * @throws InvalidTypeException If unable to complete this conversion
     */
    private static long readLong(String text) throws InvalidTypeException {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Long.class, String.class);
        }
    }

    /**
     * Attempts to turn the text into a double.
     *
     * @param text The text
     *
     * @return An object of subtype double
     *
     * @throws InvalidTypeException If unable to complete this conversion
     */
    private static double readDouble(String text) throws InvalidTypeException {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Double.class, String.class);
        }
    }
    /**
     * Attempts to turn the text into a float.
     *
     * @param text The text
     *
     * @return An object of subtype float
     *
     * @throws InvalidTypeException If unable to complete this conversion
     */
    private static float readFloat(String text) throws InvalidTypeException {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Float.class, String.class);
        }
    }

    /**
     * Attempts to turn the text into a boolean.
     *
     * @param text The text
     *
     * @return An object of subtype boolean
     *
     * @throws InvalidTypeException If unable to complete this conversion
     */
    private static boolean readBoolean(String text) throws InvalidTypeException {
        try {
            return Boolean.parseBoolean(text);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Boolean.class, String.class);
        }
    }
}
