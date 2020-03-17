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
        List<Transaction> transactions = new ArrayList<>();
        CategoryCache categoryCache = CategoryCache.getInstance();
        Category category = categoryCache.readByName(categoryName);

        if (category != null) {
            TransactionCache cache = TransactionCache.getInstance();
            transactions = cache.readByCategoryID(category.getId());
        }
        return transactions;
    }
}
