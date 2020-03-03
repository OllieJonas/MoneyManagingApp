package me.csed2.moneymanager.main;

import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionBuilder;

public class Main {

    public Main() {
        System.out.println("Hello world!");
        int i = 2;
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