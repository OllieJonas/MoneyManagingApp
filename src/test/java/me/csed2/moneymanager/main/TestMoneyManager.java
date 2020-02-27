package me.csed2.moneymanager.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import me.csed2.moneymanager.main.Main;

public class TestMoneyManager {

    /**
     * Testing if JUnit works
     */
    @Test
    public void helloWorldTest() {
        Main main = new Main();
        assertEquals(main.multiply(3,2), 6);
        // assertEquals(main.multiply(3,2), 7);
    }
}
