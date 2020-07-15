package me.csed2.moneymanager.main;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void bestConsoleTest() {
        assertThat(Main.bestVideoGameConsole(false))
                .isEqualToIgnoringCase("Xbox");

        assertThat(Main.bestVideoGameConsole(true))
                .isEqualToIgnoringCase("PS4");
    }
}
