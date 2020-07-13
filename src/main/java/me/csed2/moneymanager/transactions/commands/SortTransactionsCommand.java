package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionArgType;

import java.util.Comparator;
import java.util.function.Function;

public class SortTransactionsCommand implements Command<CachedList<Transaction>> {

    private TransactionArgType argType;
    private boolean reversed;

    public SortTransactionsCommand(TransactionArgType argType, boolean reversed) {
        this.argType = argType;
        this.reversed = reversed;
    }

    @Override
    public CachedList<Transaction> execute(App app) {
        Comparator<Transaction> comparator = getComparator(argType);
        CachedList<Transaction> sortedList = app.getTransactionCache().sort(comparator);
        app.render(sortedList.getReport());
        return sortedList;
    }

    private Comparator<Transaction> getComparator(TransactionArgType argType) {
        if (argType == TransactionArgType.NAME) {
            return Comparator.comparing(Transaction::getName);
        } else if (argType == TransactionArgType.AMOUNT) {
            return Comparator.comparingInt(Transaction::getAmount);
        } else if (argType == TransactionArgType.ID) {
            return Comparator.comparingInt(Transaction::getId);
        } else if (argType == TransactionArgType.CREATED) {
            return Comparator.comparing(Transaction::getDate);
        } else if (argType == TransactionArgType.VENDOR) {
            return Comparator.comparing(Transaction::getVendor);
        } else {
            return null;
        }
    }
}

