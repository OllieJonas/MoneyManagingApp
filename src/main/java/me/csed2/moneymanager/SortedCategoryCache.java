package me.csed2.moneymanager;

import me.csed2.moneymanager.cache.SortedCache;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SortedCategoryCache extends SortedCache<Category> {

    public SortedCategoryCache(CategoryCache cache) {
        super(cache);
    }

    public List<Category> sortByBudget(boolean reversed) {
        List<Category> list = cache.asList();
        list.sort(Comparator.comparingInt(Category::getBudget));

        if (reversed) {
            Collections.reverse(list);
        }

        return list;
    }

    public List<Category> sortByDate(boolean reversed) {
        List<Category> list = cache.asList();
        list.sort(Comparator.comparing(Category::getCreated));

        if (reversed) {
            Collections.reverse(list);
        }
        return list;
    }
}
