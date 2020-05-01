package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ListTransactionsCommand implements Function<App, List<Transaction>> {

    private final String categoryName;

    public ListTransactionsCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public List<Transaction> apply(App app) {
        CachedList<Category> cache = app.getCategoryCache();
        List<Transaction> transactions = new ArrayList<>();

        if (categoryName.equalsIgnoreCase("ALL")) {
            transactions = app.getTransactionCache().asList();
        } else {
            Optional<Category> category = cache.search(categoryName);
            if (category.isPresent()) {
                transactions = app.getTransactionCache().parallelSearch(cat -> cat.getCategory().equalsIgnoreCase(categoryName));
            }
        }
        return transactions;
    }
}
