package net.coljate.map;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import net.coljate.collection.MutableCollection;
import net.coljate.map.impl.MutableWrappedHashMap;
import net.coljate.map.impl.MutableWrappedMap;

/**
 *
 * @author ollie
 */
public interface MutableMap<K, V> extends Map<K, V>, MutableCollection<Entry<K, V>> {

    V put(K key, V value);

    boolean remove(Object key, Object value);

    @Override
    MutableEntry<K, V> getEntry(Object key);

    default boolean add(final K key, final V value) {
        return !this.containsKey(key)
                && this.putIfAbsent(key, value) == null;
    }

    default boolean add(final Entry<K, V> entry) {
        return this.add(entry.key(), entry.value());
    }
    
    default boolean addAll(final Map<? extends K, ? extends V> map) {
        boolean addedAny = false;
        for (final Entry<? extends K, ? extends V> entry : map) {
            addedAny |= this.add(entry.key(), entry.value());
        }
        return addedAny;
    }

    default Map<K, V> putAll(final Map<? extends K, ? extends V> map) {
        final MutableMap<K, V> put = MutableMap.createHashMap(map.count());
        for (final Entry<? extends K, ? extends V> entry : map) {
            put.put(entry.key(), this.put(entry.key(), entry.value()));
        }
        return put;
    }

    default V putIfAbsent(final K key, final V value) {
        final V current = this.get(key);
        return current == null
                ? this.put(key, value)
                : current;
    }

    /**
     * Compute a new value, given the key and previous value.
     *
     * Note that this method differs to {@link java.util.Map#compute} in that a null computed value will be stored
     * against the key, instead of causing the entry to be evicted.
     *
     * @param key
     * @param compute
     * @return
     */
    default V compute(final K key, final BiFunction<K, V, V> compute) {
        final V previous = this.get(key);
        final V replacement = compute.apply(key, previous);
        this.put(key, replacement);
        return replacement;
    }

    default V computeIfAbsent(final K key, final Function<K, V> supplier) {
        final V current = this.get(key);
        if (current == null) {
            final V newValue = supplier.apply(key);
            if (newValue != null) {
                this.put(key, newValue);
                return newValue;
            }
        }
        return current;
    }

    default boolean replace(final K key, final V expectedValue, final V replacementValue) {
        final Entry<K, V> current = this.getEntry(key);
        if (current == null || !Objects.equals(current.value(), expectedValue)) {
            return false;
        }
        this.put(key, replacementValue);
        return true;
    }

    @Deprecated
    default boolean remove(final Object entry) {
        return entry instanceof Entry
                && this.remove((Entry) entry);
    }

    default boolean remove(final Entry<?, ?> entry) {
        return this.remove(entry.key(), entry.value());
    }

    default V evict(final Object key) {
        final Entry<K, V> entry = this.getEntry(key);
        return entry != null && this.remove(entry)
                ? entry.value()
                : null;
    }

    default boolean removeAllMatchingEntries(final BiPredicate<? super K, ? super V> predicate) {
        boolean removed = false;
        for (final Iterator<Entry<K, V>> iterator = this.iterator(); iterator.hasNext();) {
            final Entry<K, V> entry = iterator.next();
            if (predicate.test(entry.key(), entry.value())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    @Override
    default Spliterator<Entry<K, V>> spliterator() {
        return Map.super.spliterator();
    }

    static <K, V> MutableMap<K, V> viewOf(final java.util.Map<K, V> map) {
        return MutableWrappedMap.viewOf(map);
    }

    static <K, V> MutableMap<K, V> createHashMap() {
        return MutableWrappedHashMap.create();
    }

    static <K, V> MutableMap<K, V> createHashMap(final int initialCapacity) {
        return MutableWrappedHashMap.create(initialCapacity);
    }

    static <K, V> MutableMap<K, V> copyIntoHashMap(final java.util.Map<K, V> map) {
        return MutableWrappedHashMap.copyOf(map);
    }

}
