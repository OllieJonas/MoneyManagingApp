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
public class BudgetBuilder {

    private String name;

    private int budget;

    private ArrayList<MonthsTransactionsBuilder> transPerMonth = new ArrayList<>();

    private static ImmutableBiMap<Integer, String> MONTH_INT;

    static {
        MONTH_INT = new ImmutableBiMap.Builder<Integer, String>()
                .put(0, "January")
                .put(1, "February")
                .put(2, "March")
                .put(3, "April")
                .put(4, "May")
                .put(5, "June")
                .put(6, "July")
                .put(7, "August")
                .put(8, "September")
                .put(9, "October")
                .put(10, "November")
                .put(11, "December")
                .build();
    }

    /**
     * assigns values to variables in the budget
     * @param name
     */
    public BudgetBuilder(String name) {
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

    public static String getMonthFromInt(int month) {
        return MONTH_INT.get(month);
    }

    public static int getIntFromMonth(String month) {
        return MONTH_INT.inverse().get(month);
    }
}


