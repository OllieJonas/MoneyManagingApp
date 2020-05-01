package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.Date;
import java.util.function.Function;

public class AddTransactionCommand implements Function<App, Boolean> {

    private final String categoryName;
    private final String name;
    private final int amount;
    private final String vendor;
    private final String[] notes;

    public AddTransactionCommand(String categoryName, String name, int amount, String vendor, String[] notes) {
        this.categoryName = categoryName;
        this.name = name;
        this.amount = amount * 100;
        this.vendor = vendor;
        this.notes = notes;
    }

    @Override
    public Boolean apply(App app) {
        CachedList<Category> categoryCache = app.getCategoryCache();
        CachedList<Transaction> transactionCache = app.getTransactionCache();
        
        if (categoryCache.exists(categoryName)) {

            transactionCache.add(new Transaction.Builder(name)
            .withAmount(amount)
            .withVendor(vendor)
            .withDate(new Date())
            .withNotes(notes)
            .withCategoryName(categoryName)
            .build());

            transactionCache.save("transactions.json");
            return true;
        }
        return false;
    }
}
