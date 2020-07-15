package me.csed2.moneymanager.main;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MainTest {

    private static Main subject;

    @BeforeAll
    public static void init() {
        subject = new Main();
    }

    @Test
    public void bestConsoleTest() {
        assertThat(subject.bestVideoGameConsole(false))
                .isEqualToIgnoringCase("Xbox");

        assertThat(subject.bestVideoGameConsole(true))
                .isEqualToIgnoringCase("PS4");
    }
}
