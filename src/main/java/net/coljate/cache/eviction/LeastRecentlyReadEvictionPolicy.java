package net.coljate.cache.eviction;

import java.util.Iterator;

import net.coljate.list.impl.WrappedLinkedHashSetQueue;
import net.coljate.util.iterator.Iterators;

/**
 *
 * @author Ollie
 */
public class LeastRecentlyReadEvictionPolicy implements CacheEvictionPolicy {

    private final WrappedLinkedHashSetQueue<Object> queue = new WrappedLinkedHashSetQueue<>();
    private final int capacity;

    public LeastRecentlyReadEvictionPolicy(final int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Negative capacity " + capacity + "!");
        }
        this.capacity = capacity;
    }

    @Override
    public Iterator<Object> notifyRead(final Object key) {
        queue.add(key);
        final int evictions = queue.count() - capacity;
        return evictions > 0
                ? Iterators.first(queue.iterator(), evictions)
                : Iterators.empty();
    }

    @Override
    public Iterator<Object> notifyWrite(final Object key) {
        return Iterators.empty();
    }

    @Override
    public Iterator<Object> notifyRemove(final Object key) {
        queue.removeFirst(key);
        return Iterators.empty();
    }

    @Override
    public void notifyClear() {
        queue.clear();
    }

}
