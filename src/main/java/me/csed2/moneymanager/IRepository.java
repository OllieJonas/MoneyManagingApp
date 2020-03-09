package me.csed2.moneymanager;

import java.util.List;

public interface IRepository<T, K> {

    List<T> asList();

    T readById(K id);

    void add(T entity);

    T update(T entity);

    T remove(T entity);

    void orderById();

    void setList(List<T> repo);

}
