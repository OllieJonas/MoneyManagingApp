package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryBuilder;
import me.csed2.moneymanager.categories.CategoryCache;

import java.util.Date;
import java.util.function.Supplier;

public class AddCategoryCommand implements Supplier<Boolean> {

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
    public Boolean get() {

        CategoryCache cache = CategoryCache.getInstance();

        CategoryBuilder builder = new CategoryBuilder(name)
                .withId(id)
                .withCreationDate(created)
                .withBudget(budget);

        Category category = builder.build();

        cache.add(category);

        cache.save();

        return true;
    }
}
