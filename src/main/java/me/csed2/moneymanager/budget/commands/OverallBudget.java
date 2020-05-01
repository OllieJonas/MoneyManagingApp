package me.csed2.moneymanager.budget.commands;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.budget.MonthlyBudget;
import me.csed2.moneymanager.budget.BudgetStore;
import me.csed2.moneymanager.main.App;

import java.util.ArrayList;
import java.util.function.Consumer;

@Getter
@Setter
/**
 * this finds the overall budget and prints it out
 */
public class OverallBudget implements Consumer<App> {

    int monthFor;
    double allSpent;
    double allBudget;

    ArrayList<MonthlyBudget> budgetArr;

    public OverallBudget(int monthFor){
        this.monthFor = monthFor;
        this.budgetArr = BudgetStore.getBudStore();
        BudgetStore.findBudget("Overall", monthFor);
        this.allBudget = BudgetStore.catBud;
    }

    public void trackAll(){
        for(MonthlyBudget each: budgetArr){
            allSpent += each.getTotalSpent(monthFor);
        }
    }

    public String displayOverall(){
        return "Your overall budget this month was:" + allBudget + "\n" +
        "You have spent: " + allSpent + "\n" +
        "Therefore you have " + (allBudget-allSpent) + " left and have spent " + (((double)allSpent/(double)allBudget)*100) + " so far";
    }


    @Override
    public void accept(App app) {
        trackAll();
        app.render(displayOverall());
    }
}
