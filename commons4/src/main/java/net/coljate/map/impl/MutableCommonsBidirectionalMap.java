package net.coljate.map.impl;

import net.coljate.map.MutableBidirectionalMap;
import net.coljate.map.MutableEntry;

/**
 *
 * @author Ollie
 */
public class MutableCommonsBidirectionalMap<K, V>
        extends CommonsBidirectionalMap<K, V>
        implements MutableBidirectionalMap<K, V> {

    private MutableCommonsBidirectionalMap<V, K> inverse;

    protected MutableCommonsBidirectionalMap(final org.apache.commons.collections4.BidiMap<K, V> map) {
        this(map, null);
    }

    protected MutableCommonsBidirectionalMap(final org.apache.commons.collections4.BidiMap<K, V> map, final MutableCommonsBidirectionalMap<V, K> inverse) {
        super(map, inverse);
        if (map instanceof org.apache.commons.collections4.bidimap.UnmodifiableBidiMap) {
            throw new IllegalArgumentException("Map is unmodifiable: " + map);
        }
        this.inverse = inverse;
    }

    @Override
    public V put(final K key, V value) throws DuplicateValueException {
        return map.put(key, value);
    }

    @Override
    public boolean remove(final Object key, final Object value) {
        return map.remove(key, value);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public MutableEntry<K, V> getEntry(final Object key) {
        return this.containsKey(key)
                ? new MutableCommonsBidirectionalMapEntry((K) key)
                : null;
    }

    @Override
    public MutableBidirectionalMap<V, K> inverse() {
        return inverse == null
                ? inverse = new MutableCommonsBidirectionalMap<>(map.inverseBidiMap(), this)
                : inverse;
    }

    private final class MutableCommonsBidirectionalMapEntry extends ViewEntry<K, V> implements MutableEntry<K, V> {

        MutableCommonsBidirectionalMapEntry(final K key) {
            super(key, MutableCommonsBidirectionalMap.this);
        }

        @Override
        public void setValue(final V value) {
            MutableCommonsBidirectionalMap.this.put(this.key(), value);
        }

    }

}