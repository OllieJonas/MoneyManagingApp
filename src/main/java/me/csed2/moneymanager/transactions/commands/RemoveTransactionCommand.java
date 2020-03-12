package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.categories.commands.RemoveCategoryCommand;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;

public class RemoveTransactionCommand implements ICommand<Boolean> {

    private final String categoryName;

    private final String transactionName;

    public RemoveTransactionCommand(String categoryName, String transactionName) {
        this.categoryName = categoryName;
        this.transactionName = transactionName;

    }
    @Override
    public Boolean execute() {
        CategoryRepository repository = CategoryRepository.getInstance();
        Category category = repository.readByName(categoryName);
        if (category != null) {
            Transaction transaction = category.getTransactionByName(transactionName);

            if (transaction != null) {
                category.removeTransaction(transaction);
                repository.update(category);
                repository.save();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
