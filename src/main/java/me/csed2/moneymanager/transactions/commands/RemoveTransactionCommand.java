package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.function.Function;

public class RemoveTransactionCommand implements Command<Boolean> {

    private final String transactionName;

    public RemoveTransactionCommand(String transactionName) {
        this.transactionName = transactionName;

    }
    @Override
    public Boolean execute(App app) {
        CachedList<Transaction> transactionCache = app.getTransactionCache();

        if (transactionCache.exists(transactionName)) {

            transactionCache.remove(transactionName);
            transactionCache.save("transactions.json");
            return true;
        }
        return false;
    }
}
