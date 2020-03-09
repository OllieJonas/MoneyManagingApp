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

    public void openMenu(Menu menu) {
        previousMenu = currentMenu;
        currentMenu = menu;
        menu.open();
    }

    public synchronized void exit() {
        System.out.println("Exiting program...");
        reader.close();
        System.exit(0);
    }
}
