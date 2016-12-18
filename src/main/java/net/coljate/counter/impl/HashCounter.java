package net.coljate.counter.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.coljate.counter.AbstractCounter;
import net.coljate.counter.Counter;
import net.coljate.counter.ImmutableCounter;
import net.coljate.counter.MutableCounter;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.util.Iterators.EnhancedIterator;
import net.coljate.util.Suppliers;

/**
 *
 * @author ollie
 */
public class HashCounter<T>
        extends AbstractCounter<T>
        implements Counter<T> {

    private final Map<T, Integer> map;

    protected HashCounter(final Map<T, Integer> count) {
        this.map = count;
    }

    @Override
    public int count(final Object object) {
        return Suppliers.firstNonNull(map.getIfPresent(object), 0);
    }

    @Override
    public Map<T, Integer> countElements() {
        return map;
    }

    @Override
    public boolean contains(final Object object) {
        return Counter.super.contains(object);
    }

    protected static boolean isPositive(final Integer integer) {
        return integer != null && integer > 0;
    }

    @Override
    public Iterator<T> iterator() {
        return this.enhancedIterator();
    }

    protected EnhancedIterator<T> enhancedIterator() {
        return new MultisetIterator();
    }

    @Override
    public MutableCounter<T> mutableCopy() {
        return new MutableHashCounter<>(map.mutableCopy(), true);
    }

    @Override
    public ImmutableCounter<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private final class MultisetIterator implements EnhancedIterator<T> {

        final Iterator<Entry<T, Integer>> mapIterator = map.iterator();
        T currentElement;
        int currentCount;

        @Override
        public boolean hasNext() {
            while (currentCount == 0 && mapIterator.hasNext()) {
                final Entry<T, Integer> entry = mapIterator.next();
                currentElement = entry.key();
                currentCount = entry.value();
            }
            return currentCount > 0;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            currentCount--;
            return currentElement;
        }

        @Override
        public T current() {
            return currentElement;
        }

    }

}