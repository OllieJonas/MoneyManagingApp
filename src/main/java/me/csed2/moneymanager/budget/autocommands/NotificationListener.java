package me.csed2.moneymanager.budget.autocommands;

import me.csed2.moneymanager.budget.*;
import me.csed2.moneymanager.utils.Notifications;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("WeakerAccess")
public class NotificationListener {

    private static int catBud;
    private static int catSpent;
    private BudgetCachedList list;

    public NotificationListener(BudgetCachedList list) {
        this.list = list;
    }

    public static void checkCat(String name) {

    }

    /**
     * This checks to see if the user is close to over, or on their budget in the specific category that the user just interacted with category
     * If they are over then a notification is sent
     * @param name
     */
    public void checkCat(String name, BudgetDate date) {
        for (Budget each : list.asImmutableList()) {
            if (each.getName().equals(name) && each.getDate().equals(date)) {
                catBud = each.getBudgetSize();
                catSpent = each.getTotalSpent();
                double warning = catBud * 0.9;
                if (catSpent == catBud) {
                    Notifications.displayNotification("You have reached your Budget limit in " + each.getName(), TrayIcon.MessageType.NONE);
                } else if (catSpent >= catBud) {
                    Notifications.displayNotification("You are over your Budget limit in" + each.getName(), TrayIcon.MessageType.NONE);
                } else if (catSpent >= warning) {
                    Notifications.displayNotification("This is a warning, you are approaching your budget in " + each.getName(), TrayIcon.MessageType.NONE);
                }
            }
        }
    }

    /**
     * this checks all categories to see if the user is over in any of them
     */
    public void checkAll() {
        for (Budget each : list.asImmutableList()) {
            checkCat(each.getName(), BudgetDate.CURRENT_DATE);
        }
    }




}
