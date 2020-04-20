package me.csed2.moneymanager.main;

import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.ui.MenuList;
import me.csed2.moneymanager.ui.StageMenuList;
import me.csed2.moneymanager.subscriptions.SubscriptionNotificationDispatcher;
import me.csed2.moneymanager.utils.Notifications;

import java.awt.*;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {

        new MenuList();
        new StageMenuList();

        new App();

        new AuthServerManager();

        // Testing Frame
        App.getInstance().render(MenuList.MAIN);
        // End of Testing

        Notifications.displayNotification("Hello World", TrayIcon.MessageType.NONE);
    }

    public static void main(String[] args) {
        new Main();
    }
}