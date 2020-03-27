package me.csed2.moneymanager.cache;

import com.google.common.collect.ImmutableList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
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

    /**
     * The wrapped list of items.
     */
    protected List<T> items;

    /**
     * Default constructor, used to initialise the variables.
     */
    protected Cache() {
        this.items = new ArrayList<>();
    }

    /**
     * Load a JSON file into the items list, implemented in subclasses.
     *
     * TODO: Maybe find some clever trick to do this in this class without giving it to subclasses?
     *
     * @param fileName The filename in question
     * @throws FileNotFoundException If the file can't be found
     */
    public abstract void load(String fileName) throws FileNotFoundException;

    /**
     * Saves any data from the items list into the JSON file. Implemented in subclasses.
     *
     * TODO: See above
     *
     * @param fileName The filename in question
     */
    public abstract void save(String fileName);

    /**
     * Add an item to the list.
     *
     * @param entity The item in question.
     */
    public void add(T entity) {
        items.add(entity);
    }

    /**
     * Updates a given category based on its ID. Essentially just removes it and re-adds it to the list.
     *
     * @param entity The entity in the list
     * @return The new entity
     */
    public T update(T entity) {
        items.removeIf(item -> entity.getId() == item.getId());
        items.add(entity);
        return entity;
    }

    /**
     * Removes an item from the list.
     *
     * @param entity The entity from the list
     * @return Whether it was successful
     */
    public boolean remove(T entity) {
        return items.remove(entity);
    }

    /**
     * Removes an item from the list using the Stream API based on its name.
     *
     * @param entity The item's name to remove
     * @return Whether it was successfully removed
     */
    public boolean remove(String entity) {
        AtomicBoolean removed = new AtomicBoolean(false);
        items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(entity))
                .findFirst()
                .ifPresent(item -> removed.set(items.remove(item)));
        return removed.get();
    }

    /**
     * Gets the next id based on the last thing in the list.
     *
     * Note: This assumes that the list is ordered already.
     *
     * TODO: Change this to search through the list and find the last ID?
     *
     * @return The next ID in the list
     */
    public int nextId() {
        if (items.size() > 0) {
            return items.get(items.size() - 1).getId() + 1;
        } else {
            return 1;
        }
    }

    /**
     * Checks if an item exists in the list based on any given predicate asynchronously.
     *
     * @param predicate The predicate to filter for.
     * @return Whether this item exists in the list
     */
    public boolean exists(Predicate<T> predicate) {
        return asList().parallelStream().anyMatch(predicate);
    }

    /**
     * Checks whether an item exists in the list based on its name asynchronously.
     *
     * This just calls the exists(Predicate<T> predicate) method, just automatically filling out the appropriate details.
     *
     * @param name The name to search for
     * @return Whether this item exists in the list
     */
    public boolean exists(String name) {
        return exists(item -> item.getName().equalsIgnoreCase(name));
    }

    /**
     * Iterates through the loop, printing each one as a formatted string.
     */
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

    /**
     * Return the items as an Immutable List.
     *
     * @return The items as an Immutable List
     */
    public ImmutableList<T> asImmutableList() {
        return ImmutableList.copyOf(items);
    }

    /**
     * Get the items.
     *
     * @return The items
     */
    public List<T> asList() {
        return items;
    }
}
