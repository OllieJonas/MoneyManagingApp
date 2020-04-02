package me.csed2.moneymanager.budget.Commands;

import me.csed2.moneymanager.budget.BudgetStore;

/**
 * this prints out the budget a user has chosen to see
 */
public class SeeBudgets{

    String budName;
    int month;

    public SeeBudgets(String chosenBud, int month) {
        this.budName = chosenBud;
        this.month = month;
    }

    /**
     * finds the budget the user wants to see
     */
    public void findBudget(){
        BudgetStore.findBudget(budName, month);
        System.out.println(budName + "     " + month);
        System.out.println("for the category :"+ BudgetStore.catName);
        System.out.println("Budget is: " + BudgetStore.catBud);
        System.out.println("total spent is: " + BudgetStore.catSpent);
        System.out.println("amount of budget left is: " +  (BudgetStore.catBud - BudgetStore.catSpent) );
        System.out.println("percentage of budget spent is: " + (((double)BudgetStore.catSpent/(double)BudgetStore.catBud) *100));
    }
}
