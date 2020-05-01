package me.csed2.moneymanager.budget;


import com.google.common.collect.ImmutableBiMap;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * this builds the budget objects that are stored in the budget store
 */
@Getter @Setter
public class MonthlyBudget implements Cacheable  {

    private String name;

    private double budget;

    private Set<Category> relatedCategories;

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

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public String toFormattedString() {
        return null;
    }
}


