package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.categories.CategoryCache;

import java.util.function.Supplier;

public class UpdateCategoryCommand<T> implements Supplier<Boolean> {

    private final String categoryName;

    private final CategoryArgType argType;

    private final T result;

    public UpdateCategoryCommand(String categoryName, CategoryArgType argType, T result) {
        this.categoryName = categoryName;
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean get() {
        CategoryCache repository = CategoryCache.getInstance();
        Category category = repository.readByName(categoryName);

        if (category != null) {
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
            repository.save();
            return true;
        }
        return false;
    }
}
