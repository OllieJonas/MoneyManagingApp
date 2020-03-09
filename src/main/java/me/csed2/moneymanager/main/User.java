package me.csed2.moneymanager.main;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.InputReader;

public class User {

    private InputReader reader;

    @Getter @Setter
    private Menu previousMenu;

    @Getter @Setter
    private Menu currentMenu;

    @Getter
    private static User instance;

    public User() {
        reader = new InputReader();
        reader.start();
        instance = this;
    }

    public synchronized void exit() {
        reader.close();
        System.exit(0);
    }
}
