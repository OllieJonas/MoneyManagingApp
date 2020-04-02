package me.csed2.moneymanager.budget.Commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.main.App;

import java.util.Optional;
import java.util.function.Function;

/**
 * This changes the budget for the overall catogory
 * @param <T>
 */
public class UpdateOverallBudget<T> implements Function<App, Boolean> {

    private String name = "Overall";

    private CategoryArgType argType;

    private T result;

    public UpdateOverallBudget(CategoryArgType argType, T result) {
        this.argType = argType;
        this.result = result;
    }

    @Override
    public Boolean apply(App app) {
        CachedList<Category> repository = app.getCategoryCache();

        Optional<Category> catOptional = repository.search(name);

        if (catOptional.isPresent()) {
            Category category = catOptional.get();
            category.setBudget((Integer) result);

            repository.update(category);
            repository.save("categories.json");
            return true;
        }
        return false;
    }
}
