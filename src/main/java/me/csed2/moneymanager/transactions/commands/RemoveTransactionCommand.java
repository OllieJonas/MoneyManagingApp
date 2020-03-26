package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.transactions.TransactionCache;

import java.util.function.Supplier;

public class RemoveTransactionCommand implements Supplier<Boolean> {

    private final String transactionName;

    public RemoveTransactionCommand(String transactionName) {
        this.transactionName = transactionName;

    }
    @Override
    public Boolean get() {
        TransactionCache transactionCache = TransactionCache.getInstance();

        if (transactionCache.exists(transactionName)) {
            transactionCache.remove(transactionName);
            transactionCache.save();
            return true;
        }
        return false;
    }
}
