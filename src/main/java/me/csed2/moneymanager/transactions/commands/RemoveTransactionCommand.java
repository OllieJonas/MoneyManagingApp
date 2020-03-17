package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionCache;

public class RemoveTransactionCommand implements ICommand<Boolean> {

    private final String transactionName;

    public RemoveTransactionCommand(String transactionName) {
        this.transactionName = transactionName;

    }
    @Override
    public Boolean execute() {
        TransactionCache transactionCache = TransactionCache.getInstance();

        if (transactionCache.exists(transactionName)) {
            transactionCache.remove(transactionName);
            transactionCache.save();
            return true;
        }
        return false;
    }
}
