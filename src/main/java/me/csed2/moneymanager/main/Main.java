package me.csed2.moneymanager.main;

import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.ui.MenuList;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {
        new AuthServerManager();

        App app = new App();
        app.render(MenuList.MAIN);
    }

    public static void main(String[] args) {
        new Main();
    }
}