package me.csed2.moneymanager.budget.Commands;

import me.csed2.moneymanager.budget.BudgetBuilder;
import me.csed2.moneymanager.budget.BudgetStore;
import me.csed2.moneymanager.utils.Notifications;

import java.awt.*;
import java.util.ArrayList;

public class NotificationTracker {

    private static int catBud;
    private static int catSpent;
    private static ArrayList<BudgetBuilder> budgetObj;


    /**
     * This checks to see if the user is close to over, or on their budget in the specific catogary that the user just interacted with category
     * If they are over then a notification is sent
     * @param Name
     */
    public static void checkCat(String Name) {
        budgetObj = BudgetStore.getBudStore();
        for (BudgetBuilder each : budgetObj) {
            if(each.getName().equals(Name)){
                catBud = each.getBudget();
                catSpent = each.getTotalSpent();
                double warning = catBud * 0.9;
                if (catSpent == catBud) {
                    Notifications.displayNotification("You have reached your Budget limit in " + each.getName(), TrayIcon.MessageType.NONE);
                } else if (catSpent >= catBud) {
                    Notifications.displayNotification("You are over your Budget limit in" + each.getName(), TrayIcon.MessageType.NONE);
                } else if (catSpent >= warning) {
                    Notifications.displayNotification("This is a warning, you are approching your budget in " + each.getName(), TrayIcon.MessageType.NONE);
                }
            }
        }
    }

    /**
     * this checks all categories to see if the user is over in any of them
     */
    public void checkAll(){
        budgetObj = BudgetStore.getBudStore();
        for (BudgetBuilder each : budgetObj) {
            checkCat(each.getName());
        }
    }




}
