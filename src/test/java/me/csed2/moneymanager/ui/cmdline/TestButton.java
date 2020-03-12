//package me.csed2.moneymanager.ui.cmdline;
//
//import me.csed2.moneymanager.main.User;
//import me.csed2.moneymanager.ui.Button;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.PrintStream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TestButton {
//
//    private final PrintStream backupOut = System.out;
//    private final PrintStream backupErr = System.err;
//    private final InputStream backupIn = System.in;
//
//    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//
//    @BeforeAll
//    public static void registerUsersAndStreams() {
//        new User();
//    }
//
//    @BeforeEach
//    public void registerStreams() {
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @Test
//    public void testButtonResponseWorks() {
//        new TestMenu();
//        InputStream in = new ByteArrayInputStream("1".getBytes());
//        System.setIn(in);
//        System.out.println(outContent.toString());
//    }
//
//    @AfterEach
//    public void resetStreams() {
//        System.setIn(backupIn);
//        System.setOut(backupOut);
//        System.setErr(backupErr);
//    }
//
//    private static class TestMenu extends CMDMenu {
//
//        public TestMenu() {
//            super("Test");
//        }
//
//        @Override
//        public void addButtons() {
//            addButton(new Button("foo", user -> System.out.println("bar")));
//        }
//    }
//}
