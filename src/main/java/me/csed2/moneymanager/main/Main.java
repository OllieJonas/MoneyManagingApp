package me.csed2.moneymanager.main;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.subscriptions.SubscriptionCache;
import me.csed2.moneymanager.transactions.TransactionCache;
import me.csed2.moneymanager.ui.gui.DisplayMenu;
import me.csed2.moneymanager.utils.Notifications;

import java.awt.*;
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
            new CategoryCache().load("categories.json");
            new TransactionCache().load("transactions.json");
            new SubscriptionCache().load("subscriptions.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Notifications.displayNotification("hi", TrayIcon.MessageType.NONE);

        //Testing Frame
        User.getInstance().openMenu(new MainMenu());
        //End of Testing
    }

    public static void main(String[] args) {
        new Main();
    }
}