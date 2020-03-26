package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionCache;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ListTransactionsCommand implements Supplier<List<Transaction>> {

    private final String categoryName;

    public ListTransactionsCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public List<Transaction> get() {
        CategoryCache cache = CategoryCache.getInstance();
        List<Transaction> transactions = new ArrayList<>();

        if (categoryName.equalsIgnoreCase("ALL")) {
            transactions = TransactionCache.getInstance().asList();
        } else {
            Category category = cache.readByName(categoryName);
            if (category != null) {
                transactions = TransactionCache.getInstance().readByCategory(categoryName);
            }
        }
        return transactions;
    }
}
