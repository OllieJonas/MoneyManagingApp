package me.csed2.moneymanager.budget.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;

import java.util.Optional;
import java.util.function.Function;

/**
 * This changes the budget for the overall category
 */
public class UpdateOverallBudget implements Command<Boolean> {

    private final String NAME = "Overall";

    private int result;

    public UpdateOverallBudget(int result) {
        this.result = result;
    }

    @Override
    public Boolean execute(App app) {
        CachedList<Category> repository = app.getCategoryCache();

        Optional<Category> catOptional = repository.search(NAME);

        if (catOptional.isPresent()) {
            Category category = catOptional.get();
            category.setBudget(result);

            repository.update(category);
            repository.save("categories.json");
            return true;
        }
        return false;
    }
}
