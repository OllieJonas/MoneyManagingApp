package me.csed2.moneymanager.utils;

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
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(repeat).repeat(Math.max(0, times)));
        System.out.println(builder.toString());
    }

    public static void printBorder(BorderType borderType, char border, int times) {
        if (borderType == BorderType.TOP) {
            repeatPrint(border, times);
            System.out.print("\n");

        } else {
            System.out.print("\n");
            repeatPrint(border, times);
        }
    }

    public static void printBorder(BorderType borderType) {
        printBorder(borderType, DEFAULT_BORDER, DEFAULT_TIMES);
    }

    public enum BorderType {
        TOP,
        BOTTOM
    }
}
