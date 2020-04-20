package me.csed2.moneymanager.budget;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;

@Getter @Setter
public class Budget implements Cacheable {

    private String name;
    private int id;
    private BudgetDate date;
    private int totalSpent;
    private int budgetSize;
    private boolean interactable = true;

    public Budget(String name, int id, BudgetDate date, int totalSpent, int budgetSize) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.totalSpent = totalSpent;
        this.budgetSize = budgetSize;
    }

    public BudgetDate.Month getMonth() {
        return date.getMonth();
    }

    public int getYear() {
        return date.getYear();
    }

    @Override
    public String toFormattedString() {
        return toString();
    }
}
