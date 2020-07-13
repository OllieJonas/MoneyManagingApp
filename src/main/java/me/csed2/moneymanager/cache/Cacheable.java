package me.csed2.moneymanager.cache;


import java.util.Date;

/**
 * Interface implemented by anything that can be cached in the CachedList {@link CachedList} abstract class.
 *
 * This class allows common implementation of searching the cache either by name or by the ID.
 *
 * It also allows for easy access to their formatted version, in case you need to print it.
 *
 * @author Ollie
 * @since 16/3/20
 */
public interface Cacheable {

    int getId();

    String getName();

    Date getDate();

    default String toFormattedString() {
        return toString();
    }
}
