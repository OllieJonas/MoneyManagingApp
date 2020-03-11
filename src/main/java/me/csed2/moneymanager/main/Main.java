package me.csed2.moneymanager.main;

import me.csed2.moneymanager.categories.CategoryHandler;
import me.csed2.moneymanager.ui.gui.Display;
import me.csed2.moneymanager.ui.gui.DisplayExample;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {

        new User();
        new CategoryHandler();

        //Testing Frame
        User.getInstance().openMenu(Display.DISPLAY_EXAMPLE);
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