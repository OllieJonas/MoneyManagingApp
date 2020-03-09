package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryBuilder;
import me.csed2.moneymanager.command.ICommand;

public class AddCategoryCommand implements ICommand<Category> {

    private CategoryBuilder builder;

    public AddCategoryCommand(CategoryBuilder builder) {
        this.builder = builder;
    }
    @Override
    public Category execute() {
        return builder.build();
    }
}
