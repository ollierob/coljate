package net.ollie.coljate.lists;

import java.util.RandomAccess;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 */
public class WrappedArrayList<T> extends WrappedList<T> implements RandomAccess {

    @SafeVarargs
    public static <T> java.util.ArrayList<T> copyToArrayList(final T... array) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(array.length);
        for (final T element : array) {
            list.add(element);
        }
        return list;
    }

    public static <T> java.util.ArrayList<T> copyToArrayList(final java.util.Collection<? extends T> collection) {
        return new java.util.ArrayList<>(collection);
    }

    public static <T> java.util.ArrayList<T> copyToArrayList(final Collection<? extends T> collection) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(collection.size());
        collection.forEach(list::add);
        return list;
    }

    final java.util.ArrayList<T> delegate;

    protected WrappedArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    protected java.util.ArrayList<T> copyDelegate() {
        return copyToArrayList(delegate);
    }

}