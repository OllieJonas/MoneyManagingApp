package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.command.ICommand;

public class RemoveCategoryCommand implements ICommand<Boolean> {

    private final String name;

    public RemoveCategoryCommand(String name) {
        this.name = name;
    }
    @Override
    public Boolean execute() {
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
