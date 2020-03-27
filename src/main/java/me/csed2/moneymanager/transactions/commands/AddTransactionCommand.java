package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.transactions.TransactionBuilder;
import me.csed2.moneymanager.transactions.TransactionCache;

import java.util.Date;
import java.util.function.Supplier;

public class AddTransactionCommand implements Supplier<Boolean> {

    private final String categoryName;
    private final String name;
    private final int amount;
    private final String vendor;
    private final String[] notes;

    public AddTransactionCommand(String categoryName, String name, int amount, String vendor, String[] notes) {
        this.categoryName = categoryName;
        this.name = name;
        this.amount = amount;
        this.vendor = vendor;
        this.notes = notes;
    }

    @Override
    public Boolean get() {
        CategoryCache categoryCache = CategoryCache.getInstance();
        TransactionCache transactionCache = TransactionCache.getInstance();
        
        if (categoryCache.exists(categoryName)) {

            transactionCache.add(new TransactionBuilder(name)
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
