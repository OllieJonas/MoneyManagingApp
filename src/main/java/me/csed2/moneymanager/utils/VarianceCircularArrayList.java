package me.csed2.moneymanager.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementation of ArrayList that circles around the items in the list, with an added variance.
 *
 * For example, if your list contained 1,3,5 and your variance was 0 - calling list.get(3) would return 1.
 * If you then subsequently changed the variance to 1, then it would push back everything by 1 (ie. calling list.get(3)
 * would now return 3).
 *
 * @param <E> The datatype to store
 */
public class VarianceCircularArrayList<E> extends ArrayList<E> implements Iterable<E> {

    private int variance;

    /**
     * Default constructor for VarianceCircularArrayList. Used for initialising the fields.
     *
     * @param variance The variance to shift the ArrayList by.
     */
    public VarianceCircularArrayList(int variance) {
        this.variance = variance;
    }

    /**
     * Returns the item at the index, including the variance and the circular nature.
     *
     * @param index The index you would like to get the item from
     * @return The item at this index, including the variance and the circular
     */
    @Override
    public E get(int index) {
        return super.get((index + variance) % size());
    }

    /**
     * Returns the item in the list at the actual index, without including the variance and the circular nature.
     *
     * @param index The index you'd like to get the item from.
     * @return The item at this index without any additional variance.
     */
    public E getNormal(int index) {
        return super.get(index);
    }
}
