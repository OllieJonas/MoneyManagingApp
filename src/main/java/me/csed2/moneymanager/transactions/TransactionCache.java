package me.csed2.moneymanager.transactions;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.cache.commands.LoadFromDBCommand;
import me.csed2.moneymanager.cache.commands.SaveToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TransactionCache extends Cache<Transaction> {

    private static TransactionCache instance;

    public TransactionCache() {
        instance = this;
    }
    public static TransactionCache getInstance() {
        return instance;
    }

    @Override
    public void load() throws FileNotFoundException {
        this.items = CommandDispatcher.getInstance().dispatchSync(new LoadFromDBCommand<>(Transaction.class, "transactions.json"));
    }

    @Override
    public void save() {
        CommandDispatcher.getInstance().dispatchSync(new SaveToDBCommand<>("transactions.json", items));
    }

    public List<Transaction> readByCategoryID(int categoryId) {
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction : items) {
            if (idPredicate.test(categoryId, transaction.getId())) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
