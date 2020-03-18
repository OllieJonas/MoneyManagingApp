package me.csed2.moneymanager.cache;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Interface for our own custom repository.
 *
 * @param <T> The type of object stored in the repository
 */
public abstract class Cache<T extends Cacheable> {

    protected List<T> items;

    @Getter
    protected BiPredicate<String, String> namePredicate = String::equalsIgnoreCase;

    @Getter
    protected BiPredicate<Integer, Integer> idPredicate = Integer::equals;

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

    public void remove(String entity) {
        for (T item : items) {
            if (namePredicate.test(item.getName(), entity)) {
                remove(item);
                break;
            }
        }
    }

    public T readByName(String name) {
        for (T item : items) {
            if (namePredicate.test(item.getName(), name)) {
                return item;
            }
        }
        return null;
    }

    public List<T> readByName(Predicate<String> predicate) {
        List<T> list = new ArrayList<>();

        for (T item : items) {
            if (predicate.test(item.getName())) {
                list.add(item);
            }
        }
        return list;
    }

    public int nextId() {
        if(items.size() > 0){
            return items.get(items.size() - 1).getId() + 1;
        }else{
            return 0;
        }

    }

    public void orderById() {
        items.sort(Comparator.comparingInt(Cacheable::getId));
    }

    public boolean exists(String name) {
        boolean exists = false;

        for (T item : items) {
            if (namePredicate.test(name, item.getName())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public List<T> asList() {
        return items;
    }
}
