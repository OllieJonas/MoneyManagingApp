package me.csed2.moneymanager.main;

import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.ui.MenuList;
import me.csed2.moneymanager.ui.StageMenuList;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {

        new MenuList();
        new StageMenuList();
        new AuthServerManager();
        new App();

        // Testing Frame
        App.getInstance().render(MenuList.MAIN);
        // End of Testing
    }

    public static void main(String[] args) {
        new Main();
    }
}