package me.csed2.moneymanager.budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @AllArgsConstructor
public class Budget implements Cacheable {

    private String name;
    private int id;
    private int totalSpent;
    private int budgetSize;
    private BudgetDate date;
    private Set<String> relatedCategories;

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
