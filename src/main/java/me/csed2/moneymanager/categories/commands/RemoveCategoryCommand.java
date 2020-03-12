package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.command.ICommand;

public class RemoveCategoryCommand implements ICommand<Boolean> {

    private final String name;

    public RemoveCategoryCommand(String name) {
        this.name = name;
    }
    @Override
    public Boolean execute() {
        CategoryRepository repository = CategoryRepository.getInstance();

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