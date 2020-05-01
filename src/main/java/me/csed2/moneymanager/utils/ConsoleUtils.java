package me.csed2.moneymanager.utils;

/**
 * Basic utils for console printing.
 *
 * @author Ollie
 * @since 08/03/2020
 */
public class ConsoleUtils {

    public static final char DEFAULT_BORDER = '=';
    public static final int DEFAULT_TIMES = 50;

    public static void clearConsole() {
        try {
            // String os = System.getProperty("os.name");

            System.out.print("\033[H\033[2J");
            System.out.flush();

        } catch (Exception e) {
            System.out.println("Error clearing console!");
        }
    }

    public static void repeatPrint(char repeat, int times) {
        System.out.println(String.valueOf(repeat).repeat(Math.max(0, times)));
    }

    public static void printBorder(BorderType borderType, char border, int times, boolean newLine) {
        if (borderType == BorderType.TOP) {
            repeatPrint(border, times);

            if (newLine)
                System.out.print("\n");

        } else {
            if (newLine)
                System.out.print("\n");

            repeatPrint(border, times);
        }
    }

    public static void printBorder(BorderType borderType) {
        printBorder(borderType, DEFAULT_BORDER, DEFAULT_TIMES, true);
    }

    public static void printBorder(BorderType borderType, boolean newLine) {
        printBorder(borderType, DEFAULT_BORDER, DEFAULT_TIMES, newLine);
    }

    public enum BorderType {
        TOP,
        BOTTOM
    }
}
