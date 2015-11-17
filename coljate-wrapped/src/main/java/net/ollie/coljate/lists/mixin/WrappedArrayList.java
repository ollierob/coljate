package net.ollie.coljate.lists.mixin;

import java.util.RandomAccess;

import net.ollie.coljate.Collection;
import net.ollie.coljate.lists.List;

/**
 *
 * @author Ollie
 */
public interface WrappedArrayList<T> extends List<T>, RandomAccess {

    java.util.ArrayList<T> copyDelegate();

    @SafeVarargs
    static <T> java.util.ArrayList<T> copyToArrayList(final T... array) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(array.length);
        for (final T element : array) {
            list.add(element);
        }
        return list;
    }

    static <T> java.util.ArrayList<T> copyToArrayList(final java.util.Collection<? extends T> collection) {
        return new java.util.ArrayList<>(collection);
    }

    static <T> java.util.ArrayList<T> copyToArrayList(final Collection<? extends T> collection) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(collection.size());
        collection.forEach(list::add);
        return list;
    }

}
