package me.csed2.moneymanager.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class CircularArrayList<E> extends ArrayList<E> implements Iterable<E> {

    public int varianceSize;

    public CircularArrayList(boolean hasParent) {
        if (hasParent)
            this.varianceSize = 2;
        else
            this.varianceSize = 1;
    }

    @Override
    public E get(int index) {
        return super.get((index + varianceSize) % size());
    }

    @Override
    @NotNull public Iterator<E> iterator() {
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
}
