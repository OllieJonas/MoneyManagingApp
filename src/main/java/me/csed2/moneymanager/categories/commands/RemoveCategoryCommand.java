package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;

import java.util.function.Supplier;

public class RemoveCategoryCommand implements Supplier<Boolean> {

    private final String name;

    public RemoveCategoryCommand(String name) {
        this.name = name;
    }
    @Override
    public Boolean get() {
        CategoryCache repository = CategoryCache.getInstance();

        Category category = repository.readByName(name);

        if (category != null) {

            repository.remove(category);
            repository.save();

            return true;

        } else {
            return false;
        }
    }
}
