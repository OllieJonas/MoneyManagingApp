package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class RemoveCategoryCommand implements Function<App, Boolean> {

    private final String name;

    public RemoveCategoryCommand(String name) {
        this.name = name;
    }

    @Override
    public Boolean apply(App app) {
        Cache<Category> cache = App.getInstance().getCategoryCache();

        AtomicBoolean removed = new AtomicBoolean(false); // async boolean variable, see Atomic Variables

        // Performs the null check
        cache.search(name).ifPresent(cat -> removed.set(cache.remove(cat))); // If not null, remove the category from the repo and set flag to true

        cache.save("categories.json");
        return removed.get();
    }
}
