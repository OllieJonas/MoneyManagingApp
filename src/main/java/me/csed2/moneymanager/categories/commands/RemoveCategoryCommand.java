package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.CategoryCache;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class RemoveCategoryCommand implements Supplier<Boolean> {

    private final String name;

    public RemoveCategoryCommand(String name) {
        this.name = name;
    }

    @Override
    public Boolean get() {
        CategoryCache repository = CategoryCache.getInstance();

        AtomicBoolean removed = new AtomicBoolean(false); // async boolean variable, see Atomic Variables

        // Performs the null check
        Optional.ofNullable(repository.readByName(name)) // Can be null
                .ifPresent(cat -> removed.set(repository.remove(cat))); // If not null, remove the category from the repo and set flag to true

        repository.save();
        return removed.get();
    }
}
