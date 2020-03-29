package me.csed2.moneymanager.main;

import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.ui.MenuList;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {

        new App();

        new AuthServerManager();

        //Testing Frame
        App.getInstance().openMenu(MenuList.MAIN);
        //End of Testing
    }

    public static void main(String[] args) {
        new Main();
    }
}