package me.csed2.moneymanager.transactions;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.cache.commands.LoadFromDBCommand;
import me.csed2.moneymanager.cache.commands.SaveToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;

import java.io.FileNotFoundException;

public class TransactionCache extends Cache<Transaction> {

    private static TransactionCache instance;

    public TransactionCache() {
        instance = this;
    }
    public static TransactionCache getInstance() {
        return instance;
    }

    @Override
    public void load(String fileName) throws FileNotFoundException {
        this.items = CommandDispatcher.getInstance().dispatchSync(new LoadFromDBCommand<>(Transaction.class, fileName));
    }

    @Override
    public void save(String fileName) {
        CommandDispatcher.getInstance().dispatchSync(new SaveToDBCommand<>(fileName, items));
    }
}
