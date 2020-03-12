package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionArgType;

public class UpdateTransactionCommand<T> implements ICommand<Boolean> {

    private final T result;

    private final String categoryName;

    private final String transactionName;

    private final TransactionArgType argType;


    public UpdateTransactionCommand(String categoryName, String transactionName, TransactionArgType argType, T result) {
        this.categoryName = categoryName;
        this.transactionName = transactionName;
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean execute() {
        CategoryRepository repository = CategoryRepository.getInstance();
        Category category = repository.readByName(categoryName);
        if (category != null) {
            Transaction transaction = category.getTransactionByName(transactionName);
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
                    default:
                        return false; // Should never be called
                }

                category.update(transaction);
                repository.update(category);
                repository.save();

                return true;
            }
        }
        return false;
    }
}
