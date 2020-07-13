package me.csed2.moneymanager.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;

import java.util.Date;
import java.util.Set;

@Getter @Setter @AllArgsConstructor
public class Budget implements Cacheable {

    private String name;
    private int id;
    private int totalSpent;
    private int budgetSize;
    private Date date;
    private Set<String> relatedCategories;

    public BudgetDate.Month getMonth() {
        return BudgetDate.Month.of(date.getMonth());
    }

    public int getYear() {
        return date.getYear();
    }

    @Override
    public String toFormattedString() {
        return toString();
    }
}
