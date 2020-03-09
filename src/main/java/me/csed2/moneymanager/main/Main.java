package me.csed2.moneymanager.main;

import me.csed2.moneymanager.ui.cmdline.menu.MainMenu;

public class Main {

    public Main() {
        new MainMenu();
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



//I am testing this thing second try