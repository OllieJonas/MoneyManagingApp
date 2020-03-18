package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryBuilder;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.Date;
import java.util.List;

public class AddCategoryCommand implements ICommand<Boolean> {

    private final String name;
    private final int id;
    private final Date created;
    private final int budget;

    public AddCategoryCommand(String name, int budget) {
        this.name = name;
        this.id = CategoryCache.getInstance().nextId();
        this.created = new Date();
        this.budget = budget;
    }

    @Override
    public Boolean execute() {

        CategoryCache repository = CategoryCache.getInstance();

        CategoryBuilder builder = new CategoryBuilder(name)
                .withId(id)
                .withCreationDate(created)
                .withBudget(budget);

        Category category = builder.build();

        repository.add(category);

        repository.save();

        return true;
    }
}
