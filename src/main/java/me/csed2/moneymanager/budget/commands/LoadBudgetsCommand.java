package me.csed2.moneymanager.budget.commands;

import com.google.gson.Gson;
import me.csed2.moneymanager.budget.BudgetCachedList;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;

import java.util.function.Function;

public class LoadBudgetsCommand implements Function<App, BudgetCachedList> {

    private CachedList<Category> categoryList;
    private String file;

    public LoadBudgetsCommand(CachedList<Category> categoryList, String file) {
        this.categoryList = categoryList;
        this.file = file;
    }

    @Override
    public BudgetCachedList apply(App app) {
        Gson gson = new Gson();
        return null;
    }
}
