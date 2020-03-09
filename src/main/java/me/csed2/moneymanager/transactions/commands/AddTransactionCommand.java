package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;

public class AddTransactionCommand implements ICommand<Void> {

    private Transaction transaction;

    public AddTransactionCommand(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Void execute() {
        for (Category category : CategoryRepository.getInstance().asList()) {
            if (category.getId() == transaction.getCategoryId()) {
                CategoryRepository.getInstance().readById(category.getId()).addTransaction(transaction);
                CategoryRepository.getInstance().update(category);
            }
        }
        return null;
    }
}
