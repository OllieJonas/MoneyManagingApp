package me.csed2.moneymanager.utils;

import java.io.Serializable;
import java.util.*;

/**
 * Two ArrayLists that act as one.
 *
 * @author Ollie
 *
 */
@SuppressWarnings("unused")
public class ReversiblePriorityList<E> extends AbstractList<E> implements List<E>, Cloneable, Serializable {

    private static final int DEFAULT_PRIORITY_SIZE = 9;

    private transient ArrayList<E> priority;

    private transient ArrayList<E> items;

    private boolean reversed = false;

    private int prioritySize;

    private transient E current;

    private transient E next;

    public ReversiblePriorityList(int prioritySize) {
        this.prioritySize = prioritySize;
    }

    public ReversiblePriorityList() {
        this.prioritySize = DEFAULT_PRIORITY_SIZE;
    }

    public boolean addPriority(E element) {
        return priority.add(element);
    }

    public boolean addNormal(E element) {
        return items.add(element);
    }

    public void growPriorityCapacity() {

    }

    /**
     * Returns the element at the given position in this list.
     *
     * @param index Index of the element to return
     * @return The element at the specified position in the list
     * @throws IndexOutOfBoundsException If the index is outside of the list
     */
    @Override
    public E get(int index) {
        if (index >= prioritySize) {
            Objects.checkIndex(index, priority.size());
            return priority.get(index);
        } else {
            Objects.checkIndex(index, items.size());
            return items.get(index);
        }
    }

    @Override
    public E set(int index, E element) {
        return items.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        items.add(index, element);
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < priority.size(); i++) {
            if (o.equals(priority.get(i))) {
                return i;
            }
        }

        for (int i = 0; i < items.size(); i++) {
            if (o.equals(items.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = priority.size() - 1; i >= 0; i--) {
            if (o.equals(priority.get(i))) {
                return i;
            }
        }

        for (int i = items.size() - 1; i >= 0; i--) {
            if (o.equals(items.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public int size() {
        return priority.size() + items.size();
    }

    @Override
    public boolean isEmpty() {
        return priority.isEmpty() || items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
