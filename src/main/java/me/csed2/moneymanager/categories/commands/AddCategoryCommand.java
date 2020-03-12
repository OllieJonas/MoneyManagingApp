package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryBuilder;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.Date;
import java.util.List;

public class AddCategoryCommand implements ICommand<Boolean> {

    private final String name;
    private final int id;
    private final Date created;
    private final int budget;
    private final List<Transaction> transactions;

    public AddCategoryCommand(String name, int budget, List<Transaction> transactions) {
        this.name = name;
        this.id = CategoryRepository.getInstance().nextId();
        this.created = new Date();
        this.budget = budget;
        this.transactions = transactions;
    }

    @Override
    public Boolean execute() {

        CategoryRepository repository = CategoryRepository.getInstance();

        CategoryBuilder builder = new CategoryBuilder(name)
                .withId(id)
                .withCreationDate(created)
                .withBudget(budget)
                .withTransactions(transactions);

        Category category = builder.build();

        repository.add(category);

        repository.save();

        return true;
    }
}
