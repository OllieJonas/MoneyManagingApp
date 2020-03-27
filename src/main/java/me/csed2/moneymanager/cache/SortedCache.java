package me.csed2.moneymanager.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class SortedCache<T extends Cacheable> {

    protected Cache<T> cache;

    public SortedCache(Cache<T> cache) {
        this.cache = cache;
    }

    public List<T> searchByName(Predicate<String> predicate) {
        List<T> list = cache.asList();
        for (T item : cache.asList()) {
            if (predicate.test(item.getName())) {
                list.add(item);
            }
        }
        return list;
    }

    public List<T> sortByName(boolean reversed) {
        List<T> list = cache.asList();
        list.sort(Comparator.comparing(T::getName));

        if (reversed) {
            Collections.reverse(list);
        }

        return list;
    }

}
