package me.csed2.moneymanager.budget.Commands;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.budget.BudgetBuilder;
import me.csed2.moneymanager.budget.BudgetStore;

import java.util.ArrayList;

@Getter
@Setter
/**
 * this finds the overall budget and prints it out
 */
public class OverallBudget {

    int monthFor;
    int allSpent;
    int allBudget;
    ArrayList<BudgetBuilder> budgetArr;

    public OverallBudget(int monthFor){
        this.monthFor = monthFor;
        this.budgetArr = BudgetStore.getBudStore();
        BudgetStore.findBudget("Overall", monthFor);
        this.allBudget = BudgetStore.catBud;
        trackAll();
    }

    public void trackAll(){
        for(BudgetBuilder each: budgetArr){
            allSpent += each.getTotalSpent();
        }
    }

    public void displayOverall(){
        System.out.println("Your overall budget this month was:" + allBudget);
        System.out.println("You have spent: " + allSpent);
        System.out.println("Therefore you have " + (allBudget-allSpent) + " left and have spent " + (((double)allSpent/(double)allBudget)*100) + " so far");
    }


}
