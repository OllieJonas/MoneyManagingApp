package me.csed2.moneymanager.exceptions;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException(Class<?> expected, Class<?> actual) {
        super("Error: Invalid type here! We were expecting " + expected.getSimpleName() + ", but we actually got " + actual.getSimpleName());
    }

    public InvalidTypeException() {
        super("Error: Unable to convert input into valid input!");
    }

    public InvalidTypeException(Object subject, Class<?> attemptedCast) {
        super("Error: Unable to cast " + subject + " to " + attemptedCast.getSimpleName());
    }
}
