package me.csed2.moneymanager.main;

import me.csed2.moneymanager.rest.AuthServerManager;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {

        new App();

        new AuthServerManager();

        //Testing Frame
        App.getInstance().openMenu(new MainMenu());
        //End of Testing
    }

    public static void main(String[] args) {
        new Main();
    }
}