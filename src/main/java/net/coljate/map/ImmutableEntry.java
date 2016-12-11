package net.coljate.map;

/**
 *
 * @author ollie
 */
public class ImmutableEntry<K, V>
        extends AbstractEntry<K, V> {

    private final K key;
    private final V value;

    public ImmutableEntry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    @Deprecated
    public ImmutableEntry<K, V> immutableCopy() {
        return this;
    }

}
