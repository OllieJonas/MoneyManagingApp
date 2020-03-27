package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.function.Function;

public class RemoveTransactionCommand implements Function<App, Boolean> {

    private final String transactionName;

    public RemoveTransactionCommand(String transactionName) {
        this.transactionName = transactionName;

    }
    @Override
    public Boolean apply(App app) {
        Cache<Transaction> transactionCache = app.getTransactionCache();

        if (transactionCache.exists(transactionName)) {

            transactionCache.remove(transactionName);
            transactionCache.save("transactions.json");
            return true;
        }
        return false;
    }
}
