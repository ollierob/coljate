package net.ollie.sc4j.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
public final class Arrays {

    private static final Object[] EMPTY = new Object[0];

    private Arrays() {
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public static Object[] empty() {
        return EMPTY;
    }

    public static <V> Iterator<V> iterator(final V[] array) {
        return array.length == 0
                ? Iterators.empty()
                : new ArrayIterator<>(array);
    }

    public static <V> V[] concatenate(final V[] array, final Iterable<? extends V> iterable) {
        final int size = Iterables.count(iterable);
        final V[] concatenated = java.util.Arrays.copyOf(array, array.length + size);
        int index = array.length;
        for (final V value : iterable) {
            concatenated[index++] = value;
        }
        return concatenated;
    }

    public static <V> V[] concatenate(final Iterable<? extends V> iterable, final V[] array) {
        final int size = Iterables.count(iterable);
        final V[] concatenated = java.util.Arrays.copyOf(array, array.length + size);
        System.arraycopy(array, 0, concatenated, size, array.length);
        int index = 0;
        for (final V value : iterable) {
            concatenated[index++] = value;
        }
        return concatenated;
    }

    public static <V> java.util.List<V> asList(final V... elements) {
        return java.util.Arrays.asList(elements);
    }

    public static <V> java.util.List<V> asList(final Iterable<V> iterable) {
        if (iterable instanceof java.util.List) {
            return (java.util.List<V>) iterable;
        } else {
            throw new UnsupportedOperationException(); //TODO iterable -> list
        }
    }

    static final class ArrayIterator<V>
            implements Iterator<V> {

        private final V[] array;
        private int index = 0;
        private V next;

        ArrayIterator(final V[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            while (index < array.length) {
                next = array[index];
                if (next == null) {
                    index++;
                } else {
                    return true;
                }
            }
            return false;
        }

        @Override
        public V next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            index++;
            final V theNext = next;
            next = null;
            return theNext;
        }

        @Override
        public void remove() {
            array[index] = null;
        }

    }

}
