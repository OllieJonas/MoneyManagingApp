package me.csed2.moneymanager.budget.commands;

import me.csed2.moneymanager.budget.BudgetStore;
import me.csed2.moneymanager.main.App;

import java.util.function.Consumer;

/**
 * this prints out the budget a user has chosen to see
 */
public class SeeBudgets implements Consumer<App> {

    String budName;
    int month;

    public SeeBudgets(String chosenBud, int month) {
        this.budName = chosenBud;
        this.month = month;
    }

    /**
     * finds the budget the user wants to see
     */
    @Override
    public void accept(App app) {
        BudgetStore.findBudget(budName, month);

        String response = "\n" +
                budName + "     " + month + "\n" + "\n" +
                "for the category :" + BudgetStore.catName + "\n" +
                "Budget is: " + BudgetStore.catBud + "\n" +
                "total spent is: " + BudgetStore.catSpent + "\n" +
                "amount of budget left is: " + (BudgetStore.catBud - BudgetStore.catSpent) + "\n" +
                "percentage of budget spent is: " + ((double) BudgetStore.catSpent / (double) BudgetStore.catBud) * 100 + "\n";
        app.render(response);
    }
}
