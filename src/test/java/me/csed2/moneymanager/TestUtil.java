package me.csed2.moneymanager;

import lombok.Getter;
import me.csed2.moneymanager.main.App;
import org.mockito.Mockito;

public class TestUtil {

    @Getter
    private static App app;

    static {

    }


    public static String strip(String original) {
        return original.replace("\r", "").replace("\n", "");
    }
}
