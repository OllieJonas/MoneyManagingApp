package me.csed2.moneymanager.budget;

import me.csed2.moneymanager.budget.Commands.NotificationTracker;

public class BudgetTracker {

    /**
     * this is called after the user makes any changes to transactions or catogory budget to update the budget storage and to see if a notification needs to be released
     * @param name
     * @param month
     */
    public static void TrackCheck(String name, int month){
        BudgetStore.reloadSingleBudget(name, month);
        NotificationTracker.checkCat(name);
    }

    /**
     * This is called at the begininning of the program so that the budget store is loaded
     */
    public static void LoadBugetStore(){
        BudgetStore.BudgetStoreLoad();
    }
}
