package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionArgType;

import java.util.Optional;
import java.util.function.Function;

public class UpdateTransactionCommand<T> implements Function<App, Boolean> {

    private final T result;

    private final String transactionName;

    private final TransactionArgType argType;


    public UpdateTransactionCommand(String transactionName, TransactionArgType argType, T result) {
        this.transactionName = transactionName;
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean apply(App app) {
        Cache<Transaction> cache = app.getTransactionCache();

        Optional<Transaction> transOptional = cache.search(transactionName);

            if (transOptional.isPresent()) {
                Transaction transaction = transOptional.get();
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
                        Cache<Category> categoryCache = app.getCategoryCache();
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
                cache.save("transactions.json");
                return true;
            }
        System.out.println("ERROR HERE");
        return false;
    }
}
