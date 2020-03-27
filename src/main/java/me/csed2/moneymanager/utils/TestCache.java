package me.csed2.moneymanager.utils;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.categories.Category;

import java.util.Date;

public class TestCache {

    private TestCache() {
        Cache<Category> cache = new Cache<>();
        System.out.println(cache.nextId());
        cache.add(new Category("hi", 1, new Date(), 100));
        System.out.println(cache.nextId());
        System.out.println(cache.searchMatching("h").get(0).toFormattedString());
    }

    public static void main(String[] args) {
        new TestCache();
    }
}
