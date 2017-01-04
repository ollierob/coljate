package net.coljate.list;

import net.coljate.collection.SortedCollection;

/**
 *
 * @author ollie
 */
public interface SortedList<T> extends SortedCollection<T>, List<T> {

    @Override
    default T first() {
        return List.super.first();
    }

    @Override
    default T least() {
        return this.first();
    }

    @Override
    default T greatest() {
        return this.last();
    }

}
