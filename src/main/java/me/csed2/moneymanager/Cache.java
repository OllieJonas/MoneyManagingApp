package me.csed2.moneymanager;

import lombok.Getter;
import me.csed2.moneymanager.categories.Category;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Interface for our own custom repository.
 *
 * @param <T> The type of object stored in the repository
 */
public abstract class Cache<T extends Cacheable> {

    protected List<T> items;

    @Getter
    private BiPredicate<String, String> namePredicate = String::equalsIgnoreCase;

    @Getter
    private BiPredicate<Integer, Integer> idPredicate = Integer::equals;

    public Cache() {
        this.items = new ArrayList<>();
    }

    public abstract void load() throws FileNotFoundException;

    public abstract void save();

    public T readById(int id) {
        for (T item : items) {
            if (idPredicate.test(item.getId(), id)) {
                return item;
            }
        }
        return null;
    }

    public void add(T entity) {
        items.add(entity);
    }

    public T update(T entity) {
        items.removeIf(item -> entity.getId() == item.getId());
        items.add(entity);
        return entity;
    }

    public void remove(T entity) {
        items.remove(entity);
    }

    public T readByName(String name) {
        for (T item : items) {
            if (namePredicate.test(item.getName(), name)) {
                return item;
            }
        }
        return null;
    }

    public int nextId() {
        return items.get(items.size() - 1).getId() + 1;
    }

    public void orderById() {
        items.sort(Comparator.comparingInt(Cacheable::getId));
    }
}
