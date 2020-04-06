package me.csed2.moneymanager.budget;


import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;

import java.util.ArrayList;
import java.util.Date;

import static me.csed2.moneymanager.budget.MonthsTransactionsBuilder.Checker;

@Getter
@Setter
/**
 * this builds the budget objects that are stored in the budget store
 */
public class BudgetBuilder {

    private String name;

    private int budget;

    private ArrayList<MonthsTransactionsBuilder> transPerMonth = new ArrayList<>();

    private static CachedList<Category> item = App.getInstance().getCategoryCache();

    /**
     * this find the total spent for a certain category in a certain month by using the MonthsTransaction object
     * @param monthFor
     * @return total spent that month
     */
    public int getTotalSpent(int monthFor){
        for(MonthsTransactionsBuilder each: transPerMonth){
            if(each.getMonthFor() == monthFor){
                return each.getTotalSpent();
            }
        }
        return 0;
    }

    /**
     * assigns values to variables in the budget
     * @param name
     */
    BudgetBuilder(String name) {
        this.name = name;
        this.budget = item.search(name).get().getBudget();
        int currentMonth = new Date().getMonth();
        int lastMonth;
        if(currentMonth-1<0){
            lastMonth = 11;
        }else{
            lastMonth = currentMonth-1;
        }
        //the transactions over the current month and last month are collected for the user to be able to see
        transPerMonth.add(new MonthsTransactionsBuilder(name, lastMonth));
        transPerMonth.add(new MonthsTransactionsBuilder(name,currentMonth));
    }

}


