package me.csed2.moneymanager.budget.autoCommands;


import me.csed2.moneymanager.budget.BudgetBuilder;
import me.csed2.moneymanager.budget.BudgetStore;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.utils.Notifications;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;


/**
 * This class holds the methods which perform the end of month actions such as budget overflow and within budget check;
 */
public class EndOfMonthActions {
    private static ArrayList<BudgetBuilder> arrOfBud = BudgetStore.getBudStore();

    /**
     * this method changes the budget for a category if they are below there budget for that month to add it on to next month
     */
    public static void allowOverflow(){
        for(BudgetBuilder each: arrOfBud){
            int spent = each.getTotalSpent(new Date().getMonth() -1);
            int budget = each.getBudget();
            int amountUnder = budget - spent;
            if(amountUnder>0){
                int newBudget = budget+amountUnder;
                App.getInstance().getCategoryCache().search(each.getName()).get().setBudget(newBudget);
                BudgetStore.reloadSingleBudget(each.getName());
                Notifications.displayNotification("A new budget of " + newBudget + " has been set for " + each.getName()+ " using an overflow from last month", TrayIcon.MessageType.NONE);
            }
        }
    }

    /**
     * this method send a message to the user if they are within budget for their month
     */
    public static void withinBudgetCheck(){
        for(BudgetBuilder each: arrOfBud){
            int spent = each.getTotalSpent(new Date().getMonth() -1);
            int budget = each.getBudget();
            if(budget>spent){
               Notifications.displayNotification("Congratulations, for last month you were in budget for " + each.getName(), TrayIcon.MessageType.NONE);
            }
        }
    }


    /**
     * this method checks to see whether it is the end of a month by tracking the month of the overall budget was created and compareing it to the current month
     * if the overall budget is a month behind then the other end of month actions are perfromed and the date the overall budget was created is changed
     */
    public static void checkMonth(){
        if(App.getInstance().getCategoryCache().search("Overall").get().getCreated().getMonth() != new Date().getMonth()){
            withinBudgetCheck();
            allowOverflow();
            App.getInstance().getCategoryCache().search("Overall").get().setCreated(new Date());
        }
    }
}