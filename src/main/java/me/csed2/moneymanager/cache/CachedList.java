package me.csed2.moneymanager.cache;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.SerializedName;
import me.csed2.moneymanager.cache.commands.LoadFromJsonAsListCommand;
import me.csed2.moneymanager.cache.commands.SaveListToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.Subscription;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Custom data type with additional searching and sorting features.
 *
 * This class can be used to store anything that can be cached in a JSON file.
 *
 * This class makes extensive use of the java.util.function package, which was included with Java 8.
 *
 * As a reminder, it makes more sense to "tAKE the EXpresSion Of WHaT THe CODE IS TRYING To pORTRAy, aS OPpoSeD TO TryinG
 * tO unDeRStANd THe cOdE ItSElf".
 *
 * For more information, see: https://www.baeldung.com/java-8-functional-interfaces
 *
 * @author Ollie
 * @since 16/3/20
 *
 * @param <E> The type of object stored in the repository
 *
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class CachedList<E extends Cacheable> implements Collection<E> {

    /**
     * The wrapped list of items.
     */
    @SerializedName("list")
    private List<E> items;

    /**
     * Default constructor, used to initialise the variables.
     */
    public CachedList() {
        this.items = new ArrayList<>();
    }

    /**
     * Constructor passing in a list of pre-existing items
     *
     * @param list A list of items
     */
    public CachedList(List<E> list) {
        this.items = list;
    }

    /**
     * Updates a given category based on its ID. Essentially just removes it and re-adds it to the list.
     *
     * @param entity The entity in the list
     */
    public void update(E entity) {
        ((Consumer<E>) e -> items.removeIf(item -> item.getId() == e.getId()))
                .andThen(e -> items.add(e))
                .accept(entity);
    }

    /**
     * Removes an item from the list.
     *
     * @param entity The entity from the list
     * @return Whether it was successful
     */
    public boolean remove(E entity) {
        return items.remove(entity);
    }

    /**
     * Removes an item from the list using the Stream API based on its name.
     *
     * @param entity The item's name to remove
     * @return Whether it was successfully removed
     */
    public boolean remove(String entity) {
        AtomicBoolean removed = new AtomicBoolean(false); // Atomic because async
        items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(entity))
                .findFirst()
                .ifPresent(item -> removed.set(remove(item)));
        return removed.get();
    }

    /**
     * Checks if an item exists in the list based on any given predicate asynchronously.
     *
     * @param predicate The predicate to filter for.
     * @return Whether this item exists in the list
     */
    public boolean exists(Predicate<E> predicate) {
        return asList()
                .parallelStream()
                .anyMatch(predicate);
    }

    /**
     * Checks whether an item exists in the list based on its name asynchronously.
     *
     * This just calls the exists(Predicate<E> predicate) method, just automatically filling out the appropriate details.
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
        int id = 1; // Initial value of 1.
        sort(Comparator.comparingInt(E::getId)); // Ensure the list is sorted by ID, with the last ID at the end of the list
        if (items.size() > 0) // If the list is empty, return the default id of 1.
            id = items.get(items.size() - 1).getId() + 1; // Gets the ID of the last item of the list, then adds one
        return id;
    }

    /**
     * Searches the cache list based on a predicate synchronously.
     *
     * @param predicate The predicate to filter by.
     * @return An immutable (unchangeable) list containing any items that matched the predicate given.
     */
    public ImmutableList<E> search(Predicate<E> predicate) {
        return ImmutableList.copyOf(asList()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList()));
    }

    /**
     * Searches the cache list based on a predicate asynchronously.
     *
     * @param predicate The predicate to filter by.
     * @return An immutable (unchangeable) list containing any items that matched the predicate given.
     */
    public ImmutableList<E> parallelSearch(Predicate<E> predicate) {
        return ImmutableList.copyOf(asList()
                .parallelStream()
                .filter(predicate)
                .collect(Collectors.toList()));
    }

    /**
     * Searches through the list finding the first thing searching through the predicate.
     *
     * @param predicate The predicate to search for
     * @return The optional containing the value.
     */
    public Optional<E> searchFirst(Predicate<E> predicate) {
        return asList()
                .stream()
                .filter(predicate)
                .findFirst();
    }

    /**
     * Search for the first thing with the same name as the one specified.
     *
     * @param name The name to search for
     * @return The first item it finds with this name.
     */
    public Optional<E> search(String name) {
        return searchFirst(item -> item.getName().equalsIgnoreCase(name));
    }

    /**
     * Searches for anything with a name starting with the string specified.
     *
     * @param name The name to search for
     * @return A list containing the matching items.
     */
    public CachedList<E> searchMatching(String name) {
        return new CachedList<>(search(item -> item.getName().toLowerCase().startsWith(name.toLowerCase())));
    }

    /**
     * Sorts the cache list based on a comparator synchronously.
     *
     * Note the lack of parallelSort; it doesn't really make sense to sort something asynchronously
     * (otherwise we'll be waiting for other values, therefore just becoming asynchronous).
     *
     * @param comparator The comparator to sort by.
     * @return An immutable (unchangeable) sorted list using the comparator given.
     */
    public CachedList<E> sort(Comparator<E> comparator) {
        return new CachedList<>(ImmutableList.copyOf(asList()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList())));
    }

    public Stream<E> stream() {
        return asList().stream();
    }

    public Stream<E> parallelStream() {
        return asList().parallelStream();
    }

    /**
     * Iterates through the loop, printing each one as a formatted string.
     */
    public void print() {
        items.iterator()
                .forEachRemaining(item -> System.out.println(item.toFormattedString()));
    }

    /**
     * Builds a report containing the formatted string of all items in the list.
     *
     * @return The formatted string of all items in the list.
     */
    public String getReport() {
        StringBuilder builder = new StringBuilder();
        items.forEach(item -> builder.append(item.toFormattedString()).append("\n"));
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
    public CachedList<E> load(Class<E> clazz, String fileName) throws FileNotFoundException {
        this.items = CommandDispatcher.dispatchSync(new LoadFromJsonAsListCommand<>(fileName, clazz));
        return this;
    }

    /**
     * Saves any data from the items list into the JSON file. Implemented in subclasses.
     *
     * @param fileName The filename in question
     */
    public boolean save(String fileName) {
        return CommandDispatcher.dispatchSync(new SaveListToDBCommand<>(fileName, items));
    }

    /**
     * Clears the cache to be reused.
     */
    public void clear() {
        this.items = new ArrayList<>();
    }

    /**
     * Return the items as an Immutable List.
     *
     * @return The items as an Immutable List
     */
    public ImmutableList<E> asImmutableList() {
        return ImmutableList.copyOf(items);
    }

    /**
     * Get the items.
     *
     * @return The items
     */
    public List<E> asList() {
        return items;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return items.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return items.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        return items.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return items.retainAll(c);
    }
}
