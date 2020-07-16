package me.csed2.moneymanager.utils;

import static org.assertj.core.api.Assertions.*;

import me.csed2.moneymanager.TestUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleUtilsTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void init() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restore() {
        System.setOut(originalOut);
    }

    @AfterEach
    public void flush() {
        System.out.flush();
    }

    @Test
    public void confirmRepeatPrint_PrintsSuccessfully() {
        String expected = "*".repeat(10);
        ConsoleUtils.repeatPrint('*', 10);
        assertThat(TestUtil.strip(outContent.toString())).isEqualTo(expected);
    }

//    @Test
//    public void printDefaultBorder_PrintsSuccessfully() {
//        String expectedTop = "=".repeat(50) + "\n" + "\r";
//        String expectedBottom = "\n" + "=".repeat(50) + "\r";
//
//        ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP);
//        assertThat(outContent.toString()).isEqualTo(expectedTop);
//
//        ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM);
//        assertThat(outContent.toString()).isEqualTo(expectedBottom);
//    }
//
//    @Test
//    public void printCustomBorder_PrintsSuccessfully() {
//        char borderChar = 'h';
//        int repeat = 50;
//
//        String expectedTop = String.valueOf(borderChar).repeat(repeat) + "\n" + "\r";
//        String expectedBottom = "\n" + "h".repeat(50) + "\r";
//
//        ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP, borderChar, repeat, true);
//        assertThat(outContent.toString()).isEqualTo(expectedTop);
//
//        ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM, borderChar, repeat, true);
//        assertThat(outContent.toString()).isEqualTo(expectedBottom);
//    }
}
