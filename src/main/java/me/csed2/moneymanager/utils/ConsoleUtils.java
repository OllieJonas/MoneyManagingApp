package me.csed2.moneymanager.utils;

import lombok.experimental.UtilityClass;

/**
 * Basic utils for console printing.
 *
 * @author Ollie
 * @since 08/03/2020
 */
@UtilityClass
public class ConsoleUtils {

    public final char DEFAULT_BORDER = '=';
    public final int DEFAULT_TIMES = 50;

    public void repeatPrint(char repeat, int times) {
        System.out.println(String.valueOf(repeat).repeat(Math.max(0, times)));
    }

    public void printBorder(BorderType borderType, char border, int times, boolean newLine) {
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

    public void printBorder(BorderType borderType) {
        printBorder(borderType, DEFAULT_BORDER, DEFAULT_TIMES, true);
    }

    public void printBorder(BorderType borderType, boolean newLine) {
        printBorder(borderType, DEFAULT_BORDER, DEFAULT_TIMES, newLine);
    }

    public enum BorderType {
        TOP,
        BOTTOM
    }
}
