package me.csed2.moneymanager.cache;

public abstract class SortedCache<T extends Cacheable> {

    private Cache<T> cache;

    public SortedCache(Cache<T> cache) {
        this.cache = cache;
    }

}
