package me.csed2.moneymanager;

import java.util.List;

/**
 * Interface for our own custom repository.
 *
 * @param <T> The type of object stored in the repository
 * @param <K> The type of ID to recognise the items in the repository
 */
public interface IRepository<T, K> {

    List<T> asList();

    T readById(K id);

    void add(T entity);

    T update(T entity);

    T remove(T entity);

    void orderById();

    void setList(List<T> repo);

}
