package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.TransactionCache;

import java.util.function.Supplier;

public class UpdateTransactionCommand<T> implements Supplier<Boolean> {

    private final T result;

    private final String transactionName;

    private final TransactionArgType argType;


    public UpdateTransactionCommand(String transactionName, TransactionArgType argType, T result) {
        this.transactionName = transactionName;
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean get() {
        TransactionCache cache = TransactionCache.getInstance();
        Transaction transaction = cache.readByName(transactionName);
            if (transaction != null) {
                switch (argType) {
                    case NAME:
                        transaction.setName((String) result);
                        break;
                    case AMOUNT:
                        transaction.setAmount((Integer) result);
                        break;
                    case NOTES:
                        transaction.setNotes((String[]) result);
                        break;
                    case VENDOR:
                        transaction.setVendor((String) result);
                        break;
                    case CATEGORY:
                        CategoryCache categoryCache = CategoryCache.getInstance();
                        String categoryName = (String) result;

                        if (categoryCache.exists(categoryName)) {
                            transaction.setCategory(categoryName);
                            break;
                        } else {
                            return false;
                        }
                    default:
                        return false; // Should never be called
                }
                cache.update(transaction);
                cache.save();
                return true;
            }
        System.out.println("ERROR HERE");
        return false;
    }
}
