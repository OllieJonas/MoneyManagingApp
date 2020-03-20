package me.csed2.moneymanager.cache;


/**
 * Interface implemented by anything that can be cached in the Cache {@link Cache } abstract class.
 *
 * This class allows common implementation of searching the cache either by name or by the ID.
 *
 * It also allows for easy access to their formatted version, incase you need to print it.
 *
 * @author Ollie
 * @since 16/3/20
 */
public interface Cacheable {

    int getId();

    String getName();

    String toFormattedString();

    default boolean equals(Cacheable cacheable) {
        return this.getClass() == cacheable.getClass() && this.getId() == cacheable.getId();
    }

}
