package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;

import java.util.Optional;
import java.util.function.Function;

public class UpdateCategoryCommand<T> implements Command<Boolean> {

    private final String categoryName;

    private final CategoryArgType argType;

    private final T result;

    public UpdateCategoryCommand(String categoryName, CategoryArgType argType, T result) {
        this.categoryName = categoryName;
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean execute(App app) {
        CachedList<Category> repository = app.getCategoryCache();

        Optional<Category> catOptional = repository.search(categoryName);

        if (catOptional.isPresent()) {

            Category category = catOptional.get();

            switch (argType) {
                case NAME:
                    category.setName((String) result);
                    break;
                case BUDGET:
                    category.setBudget((Integer) result);
                    break;

                default:
                    return false; // Should never be called
            }

            repository.update(category);
            repository.save("categories.json");
            return true;
        }
        return false;
    }
}
