package net.coljate.cache;

import net.coljate.cache.impl.MutableCacheBackedListMultimap;
import net.coljate.collection.Collection;
import net.coljate.list.MutableList;

/**
 *
 * @author ollie
 */
public interface MutableListMultimap<K, V>
        extends ListMultimap<K, V>, MutableMultimap<K, V> {

    @Override
    MutableList<V> get(K key);

    @Override
    MutableMultimapEntry<K, V, ? extends MutableList<V>> getEntry(Object key);

    @Override
    default boolean add(final K key, final Collection<V> value) {
        this.get(key).suffixAll(value);
        return true;
    }

    static <K, V> MutableListMultimap<K, V> createLinkedListMultimap() {
        return MutableCacheBackedListMultimap.createLinkedListMultimap();
    }

}