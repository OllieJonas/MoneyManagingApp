package me.csed2.moneymanager.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementation of ArrayList that circles around the items in the list, with an added shift.
 *
 * For example, if your list contained 1,3,5 and your shift was 0 - calling list.get(3) would return 1.
 * If you then subsequently changed the shift to 1, then it would push back everything by 1 (ie. calling list.get(3)
 * would now return 3).
 *
 * @param <E> The datatype to store
 */
@SuppressWarnings("unused")
public class ShiftedCircularArrayList<E> extends ArrayList<E> implements Iterable<E> {

    /**
     * The amount to shift the ArrayList over upon calling the get method.
     */
    private int shift;

    /**
     * Default constructor for ShiftedCircularArrayList. Used for initialising the fields.
     *
     * @param shift The shift to shift the ArrayList by.
     */
    public ShiftedCircularArrayList(int shift) {
        this.shift = shift;
    }

    /**
     * Returns the item at the index, including the shift and the circular nature.
     *
     * @param index The index you would like to get the item from
     * @return The item at this index, including the shift and the circular
     */
    @Override
    public E get(int index) {
        return super.get((index + shift) % size());
    }

    /**
     * Returns the item in the list at the actual index, without including the shift and the circular nature.
     *
     * @param index The index you'd like to get the item from.
     * @return The item at this index without any additional shift.
     */
    public E getUnshifted(int index) {
        return super.get(index);
    }

    @Override
    @NotNull
    public Iterator<E> iterator() {
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
