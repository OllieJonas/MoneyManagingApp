package me.csed2.moneymanager.budget;

import lombok.Getter;
import lombok.Setter;
//import me.csed2.moneymanager.budget.Commands.OverallBudget;
//import me.csed2.moneymanager.budget.Commands.SeeBudgets;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;

import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.ui.MenuList;
import me.csed2.moneymanager.ui.StageMenuList;


import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
public class BudgetStore {

    public static String catName;
    public static int catBud;
    public static int catSpent;
    private static ArrayList<BudgetBuilder> budStore = new ArrayList<BudgetBuilder>();


    public BudgetStore() {

    }

    /**
     * this creates all the budget objects by calling the builder class
     */
    public static void  BudgetStoreLoad() {
        for (Category item : App.getInstance().getCategoryCache().asList()) {
            budStore.add(new BudgetBuilder(item.getName()));
        }
    }

    public static ArrayList<BudgetBuilder> getBudStore(){
        return budStore;
    }


    /**
     * this gets the budget object from the array
     * @param name
     * @param month
     */
    public static void findBudget(String name, int month){
        for(BudgetBuilder each: budStore){
            System.out.println(each.getName());
            if(each.getName().equals(name)){
                System.out.println("heya");
                catBud = each.getBudget();
                catSpent = each.getTotalSpent(month);
                catName = each.getName();
                return;
            }
        }
        catBud = 0;
        catSpent = 0;
        catName = " Not Found";
    }


    /**
     * this reloads the budget for the category that has just been edited or has a transaction edited
     * @param name
     */
    public static void reloadSingleBudget(String name){
        for(BudgetBuilder each: budStore){
            if(each.getName().equals(name)){
                int index = budStore.indexOf(each);
                budStore.set(index, new BudgetBuilder(each.getName()));
            }
        }
    }
}