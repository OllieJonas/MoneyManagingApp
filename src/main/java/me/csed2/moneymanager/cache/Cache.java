package me.csed2.moneymanager.cache;

import com.google.common.collect.ImmutableList;
import me.csed2.moneymanager.transactions.Transaction;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Abstract class for our own custom cache.
 *
 * This is essentially a wrapper for an ArrayList with additional features. The main purpose of this class is to allow
 * to search the wrapped list by the properties contained inside one of the Cacheable items. For example, if you wanted
 * to search a list of Transactions by the Category Name.
 *
 * At the moment, the abstract version contains searches for two things; their name and the ID. At the moment, both of
 * these are used interchangeably during the code, although we will be aiming to mainly use names, due to making it
 * easier to present for a user to read, rather than having to convert an ID into a name.
 *
 * @author Ollie, Jac
 * @since 16/3/20
 *
 * @param <T> The type of object stored in the repository
 *
 */
public abstract class Cache<T extends Cacheable> {

    protected List<T> items;

    protected Cache() {
        this.items = new ArrayList<>();
    }

    public abstract void load(String fileName) throws FileNotFoundException;

    public abstract void save(String fileName);

    public void add(T entity) {
        items.add(entity);
    }

    public T update(T entity) {
        items.removeIf(item -> entity.getId() == item.getId());
        items.add(entity);
        return entity;
    }

    public boolean remove(T entity) {
        return items.remove(entity);
    }

    public boolean remove(String entity) {
        boolean removed = false;
        for (T item : items) {
            if (item.getName().equalsIgnoreCase(entity)) {
                removed = remove(item);
                break;
            }
        }
        return removed;
    }

    public int nextId() {
        if (items.size() > 0) {
            return items.get(items.size() - 1).getId() + 1;
        } else {
            return 1;
        }
    }

    public boolean exists(Predicate<T> predicate) {
        return asList().parallelStream().anyMatch(predicate);
    }

    public boolean exists(String name) {
        return exists(item -> item.getName().equalsIgnoreCase(name));
    }

    public void print() {
        for (T item : items) {
            System.out.println(item.toFormattedString());
        }
    }

    /**
     * Searches through the list finding the first thing searching through the predicate.
     *
     * @param predicate The predicate to search for
     * @return The optional containing the value.
     */
    public Optional<T> searchFirst(Predicate<T> predicate) {
        return asList().stream().filter(predicate).findFirst();
    }

    /**
     * Searches the cache list based on a predicate asynchronously.
     *
     * @param predicate The predicate to filter by.
     * @return An immutable (unchangeable) list containing any items that matched the predicate given.
     */
    public ImmutableList<T> parallelSearch(Predicate<T> predicate) {
        return ImmutableList.copyOf(asList().parallelStream().filter(predicate).collect(Collectors.toList()));
    }

    /**
     * Searches the cache list based on a predicate synchronously.
     *
     * @param predicate The predicate to filter by.
     * @return An immutable (unchangeable) list containing any items that matched the predicate given.
     */
    public ImmutableList<T> search(Predicate<T> predicate) {
        return ImmutableList.copyOf(asList().stream().filter(predicate).collect(Collectors.toList()));
    }

    public Optional<T> search(String name) {
        return searchFirst(item -> item.getName().equalsIgnoreCase(name));
    }

    /**
     * Sorts the cache list based on a comparator synchronously. Note the lack of parallelSort; it doesn't really make sense
     * to sort something asynchronously (otherwise we're waiting on other values, therefore just becoming asynchronous).
     *
     * @param comparator The comparator to sort by.
     * @return An immutable (unchangeable) sorted list using the comparator given.
     */
    public ImmutableList<T> sort(Comparator<T> comparator) {
        return ImmutableList.copyOf(asList().stream().sorted(comparator).collect(Collectors.toList()));
    }

    public ImmutableList<T> asImmutableList() {
        return ImmutableList.copyOf(items);
    }

    public List<T> asList() {
        return items;
    }
}
