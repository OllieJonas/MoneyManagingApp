package me.csed2.moneymanager;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SortedCategoryCache {

    private final CategoryCache cache;

    public SortedCategoryCache(CategoryCache cache) {
        this.cache = cache;
    }

    public List<Category> searchByName(Predicate<String> predicate) {
        List<Category> list = cache.asList();
        for (Category category : list) {
            if (predicate.test(category.getName())) {
                list.add(category);
            }
        }
        return list;
    }

    public List<Category> sortByBudget(SortOrder sortOrder) {
        List<Category> list = cache.asList();
        list.sort(Comparator.comparingInt(Category::getBudget));

        if (sortOrder == SortOrder.DESCENDING) {
            Collections.reverse(list);
        }
        return list;
    }

    public enum SortOrder {
        ASCENDING,
        DESCENDING;
    }
}
