package me.csed2.moneymanager.utils;

import me.csed2.moneymanager.exceptions.InvalidTypeException;

public class StringReaderFactory {

    public static <T> Object parse(String text, Class<T> type) throws InvalidTypeException {
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

    private static int readInt(String text) throws InvalidTypeException {
        try {

            return Integer.parseInt(text);

        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Integer.class, String.class);
        }
    }

    private static long readLong(String text) throws InvalidTypeException {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Long.class, String.class);
        }
    }

    private static double readDouble(String text) throws InvalidTypeException {
        try {

            return Double.parseDouble(text);

        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Double.class, String.class);
        }
    }

    private static float readFloat(String text) throws InvalidTypeException {
        try {

            return Float.parseFloat(text);

        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Float.class, String.class);
        }
    }

    private static boolean readBoolean(String text) throws InvalidTypeException {
        try {

            return Boolean.parseBoolean(text);

        } catch (NumberFormatException e) {
            throw new InvalidTypeException(Boolean.class, String.class);
        }
    }
}
