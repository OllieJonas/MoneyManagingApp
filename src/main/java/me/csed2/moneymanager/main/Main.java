package me.csed2.moneymanager.main;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.transactions.TransactionCache;
import me.csed2.moneymanager.ui.gui.DisplayMenu;

import java.io.FileNotFoundException;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {

        new User();
        try {

            new CategoryCache().load();
            new TransactionCache().load();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Testing Frame
        User.getInstance().openMenu(DisplayMenu.MAIN);
        //End of Testing
    }

    /**
     * Method that multiplies 2 numbers together, used for testing JUnit
     * @param a First number
     * @param b Second number
     * @return a*b
     */
    public int multiply(int a, int b) {
        return a*b;
    }

    public static void main(String[] args) {
        new Main();
    }
}