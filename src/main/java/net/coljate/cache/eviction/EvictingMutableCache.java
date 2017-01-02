package net.coljate.cache.eviction;

import net.coljate.cache.MutableCache;
import net.coljate.cache.eviction.CacheEvictionPolicy.EvictionList;
import net.coljate.collection.Collection;
import net.coljate.map.MutableEntry;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public class EvictingMutableCache<K, V> implements MutableCache<K, V> {

    private final MutableCache<K, V> cache;
    private final CacheEvictionPolicy evictionPolicy;

    public EvictingMutableCache(final MutableCache<K, V> cache, final CacheEvictionPolicy evictionPolicy) {
        this.cache = cache;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public Set<K> keys() {
        return cache.keys();
    }

    @Override
    public Collection<V> values() {
        return cache.values();
    }

    @Override
    public MutableEntry<K, V> getEntry(final Object key) {
        final MutableEntry<K, V> entry = cache.getEntry(key);
        if (entry != null) {
            this.process(evictionPolicy.notifyRead(key));
        }
        return entry;
    }

    @Override
    public V put(final K key, final V value) {
        final V put = cache.put(key, value);
        this.process(evictionPolicy.notifyWrite(key));
        return put;
    }

    @Override
    public boolean remove(final Object key, final Object value) {
        final boolean removed = cache.remove(key, value);
        if (removed) {
            this.process(evictionPolicy.notifyRemove(key));
        }
        return removed;
    }

    @Override
    public V evict(final Object key) {
        if (cache.containsKey(key)) {
            final V evicted = cache.evict(key);
            this.process(evictionPolicy.notifyRemove(key));
            return evicted;
        } else {
            return null;
        }
    }

    @Override
    public void clear() {
        cache.clear();
        evictionPolicy.notifyClear();
    }

    @Override
    public MutableCache<K, V> mutableCopy() {
        return cache.mutableCopy();
    }

    private void process(final EvictionList eviction) {
        while (eviction.hasNext()) {
            this.evict(eviction.next());
        }
    }

}
