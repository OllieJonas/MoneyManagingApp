package me.csed2.moneymanager.cache;

import com.google.common.collect.ImmutableList;
import me.csed2.moneymanager.cache.commands.LoadFromDBCommand;
import me.csed2.moneymanager.cache.commands.SaveToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Custom data type with additional searching and sorting features.
 *
 * This class can be used to store anything that can be cached in a JSON file.
 *
 * This class makes extensive use of the java.util.function package, which was included with Java 8. As a reminder,
 * it makes more sense to take the expression of what it does, as opposed to actually understanding the details when reading.
 *
 * For more information, see: https://www.baeldung.com/java-8-functional-interfaces
 *
 * @author Ollie
 * @since 16/3/20
 *
 * @param <T> The type of object stored in the repository
 *
 */
public class Cache<T extends Cacheable> {

    /**
     * The wrapped list of items.
     */
    protected List<T> items;

    /**
     * Default constructor, used to initialise the variables.
     */
    public Cache() {
        this.items = new ArrayList<>();
    }

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
    public boolean update(T entity) {
        return ((Function<T, Boolean>) t -> items.removeIf(item -> item.getId() == t.getId())).andThen(aBoolean -> items.add(entity)).apply(entity);
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
        items.stream().filter(item -> item.getName().equalsIgnoreCase(entity)).findFirst().ifPresent(item -> removed.set(items.remove(item)));
        return removed.get();
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
     * Gets the next id based on the last thing in the list.
     *
     * Note: This assumes that the list is ordered already.
     *
     * @return The next ID in the list
     */
    public int nextId() {
        AtomicInteger id = new AtomicInteger(1);
        ifNotEmpty(cache -> id.set(cache.asList().get(items.size() - 1).getId() + 1));
        return id.get();
    }


    /**
     * Executes the consumer if the list isn't empty, with the parameter being this.
     *
     * @param consumer The consumer to execute
     */
    public void ifNotEmpty(Consumer<Cache<T>> consumer) {
        if (items.size() > 0)
            consumer.accept(this);
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
     * Searches through the list finding the first thing searching through the predicate.
     *
     * @param predicate The predicate to search for
     * @return The optional containing the value.
     */
    public Optional<T> searchFirst(Predicate<T> predicate) {
        return asList().stream().filter(predicate).findFirst();
    }

    /**
     * Search for the first thing with the same name as the one specified.
     *
     * @param name The name to search for
     * @return The first item it finds with this name.
     */
    public Optional<T> search(String name) {
        return searchFirst(item -> item.getName().equalsIgnoreCase(name));
    }

    /**
     * Searches for anything with a name starting with the string specified.
     *
     * @param name The name to search for
     * @return A list containing the matching items.
     */
    public ImmutableList<T> searchMatching(String name) {
        return search(item -> item.getName().startsWith(name));
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
     * Iterates through the loop, printing each one as a formatted string.
     */
    public void print() {
        items.iterator().forEachRemaining(t -> System.out.println(t.toFormattedString()));
    }

    /**
     * Builds a report containing the formatted string of all items in the list.
     *
     * @return The formatted string of all items in the list.
     */
    public String getReport() {
        StringBuilder builder = new StringBuilder();
        items.iterator().forEachRemaining(t -> builder.append(t.toFormattedString()).append("\n"));
        return builder.toString();
    }

    /**
     * Load a JSON file into the items list.
     *
     * NOTE: Due to type erasure (see: https://www.baeldung.com/java-type-erasure), we need to specify the class here
     * as well.
     *
     * @param fileName The filename in question
     * @throws FileNotFoundException If the file can't be found
     */
    public void load(Class<T> clazz, String fileName) throws FileNotFoundException {
        this.items = CommandDispatcher.dispatchSync(new LoadFromDBCommand<>(clazz, fileName));
    }

    /**
     * Saves any data from the items list into the JSON file. Implemented in subclasses.
     *
     * @param fileName The filename in question
     */
    public void save(String fileName) {
        CommandDispatcher.dispatchSync(new SaveToDBCommand<>(fileName, items));
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
