package me.csed2.moneymanager.budget;


import com.google.common.collect.ImmutableBiMap;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.main.App;

import java.util.ArrayList;
import java.util.Date;

/**
 * this builds the budget objects that are stored in the budget store
 */
@Getter @Setter
public class MonthlyBudget {

    private String name;

    private int budget;

    private ArrayList<MonthsTransactionsBuilder> transPerMonth = new ArrayList<>();

    /**
     * assigns values to variables in the budget
     * @param name
     */
    public MonthlyBudget(String name) {
        this.name = name;
        this.budget = App.getInstance().getCategoryCache().search(name).get().getBudget();
        int currentMonth = new Date().getMonth();
        int lastMonth = currentMonth - 1 < 0 ? 11 : currentMonth - 1;
        //the transactions over the current month and last month are collected for the user to be able to see
        transPerMonth.add(new MonthsTransactionsBuilder(name, lastMonth));
        transPerMonth.add(new MonthsTransactionsBuilder(name, currentMonth));
    }

    /**
     * this find the total spent for a certain category in a certain month by using the MonthsTransaction object
     * @param monthFor
     * @return total spent that month
     */
    public int getTotalSpent(int monthFor) {
        for (MonthsTransactionsBuilder each : transPerMonth){
            if (each.getMonthFor() == monthFor) {
                return each.getTotalSpent();
            }
        }
        return 0;
    }
}


