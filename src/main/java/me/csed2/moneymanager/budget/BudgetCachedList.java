package me.csed2.moneymanager.budget;

import me.csed2.moneymanager.budget.autocommands.NotificationListener;
import me.csed2.moneymanager.cache.CachedList;

import java.io.FileNotFoundException;

public class BudgetCachedList extends CachedList<Budget> {

    private int overallBudget;

    private Budget currentBudget;

    private NotificationListener listener;

    @Override
    public BudgetCachedList load(Class<Budget> clazz, String fileName) throws FileNotFoundException {

        return this;
    }

    public BudgetCachedList attachListener(NotificationListener listener) {
        this.listener = listener;
        return this;
    }
}
