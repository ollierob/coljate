package net.coljate.map.lazy;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import net.coljate.map.Entry;
import net.coljate.map.MutableSortedMap;
import net.coljate.map.SortedMap;
import net.coljate.set.lazy.LazySortedSet;
import net.coljate.util.iterator.Iterators;

/**
 *
 * @author ollie
 */
public class LazyFilteredSortedMap<K, V>
        extends LazyFilteredMap<K, V>
        implements SortedMap<K, V> {

    private final SortedMap<K, V> map;
    private Keys keys;

    public LazyFilteredSortedMap(final SortedMap<K, V> map, final Predicate<? super Entry<K, V>> predicate) {
        super(map, predicate);
        this.map = map;
    }

    @Override
    public LazySortedSet<K> keys() {
        return keys == null ? (keys = new Keys()) : keys;
    }

    @Override
    public MutableSortedMap<K, V> mutableCopy() {
        return SortedMap.super.mutableCopy();
    }

    @Override
    public Comparator<? super Entry<K, V>> comparator() {
        return map.comparator();
    }

    @Override
    public Entry<K, V> greatest() {
        return map.getEntry(this.keys().greatest());
    }

    private final class Keys implements LazySortedSet<K> {

        @Override
        public Iterator<K> iterator() {
            return Iterators.transform(LazyFilteredSortedMap.this.iterator(), Entry::key);
        }

        @Override
        public Comparator<? super K> comparator() {
            return (k1, k2) -> LazyFilteredSortedMap.this.comparator().compare(map.getEntry(k1), map.getEntry(k2));
        }

        @Override
        public K greatest() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
