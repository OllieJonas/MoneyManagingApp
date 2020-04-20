package me.csed2.moneymanager.budget.autocommands;

import me.csed2.moneymanager.budget.BudgetStore;

public class BudgetTracker {

    /**
     * this is called after the user makes any changes to transactions or category budget to update the budget storage and to see if a notification needs to be released
     * @param name
     * @param month
     */
    public static void trackCheck(String name, int month) {
        BudgetStore.reloadSingleBudget(name);
        NotificationListener.checkCat(name);
    }

    /**
     * This is called at the beginning of the program so that the budget store is loaded
     */
    public static void loadBudgetStore() {
        BudgetStore.BudgetStoreLoad();
    }
}
