package me.csed2.moneymanager.main;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.subscriptions.SubscriptionCache;
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

        new AuthServerManager();

        try {
            new CategoryCache().load();
            new TransactionCache().load();
            new SubscriptionCache().load();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Testing Frame
        User.getInstance().openMenu(new MainMenu());
        //End of Testing
    }

    public static void main(String[] args) {
        new Main();
    }
}