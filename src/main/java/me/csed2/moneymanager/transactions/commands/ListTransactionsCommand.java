package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionCache;

import java.util.ArrayList;
import java.util.List;

public class ListTransactionsCommand implements ICommand<List<Transaction>> {

    private final String categoryName;

    public ListTransactionsCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public List<Transaction> execute() {
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
