package net.coljate.tree.impl;

import java.util.function.Supplier;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.set.Set;
import net.coljate.tree.MutableTree;
import net.coljate.tree.Tree;
import net.coljate.util.Functions;
import net.coljate.util.SelfTyped;

/**
 *
 * @author ollie
 */
public class Subtree<K, V, T extends Subtree<K, V, T>>
        implements Tree<K, V, T>, Entry<K, V>, SelfTyped<T> {

    private final K key;
    private final Supplier<? extends V> getValue;
    private final Set<? extends T> children;

    public Subtree(final K key, final Supplier<? extends V> getValue, final Set<? extends T> children) {
        this.key = key;
        this.getValue = getValue;
        this.children = children;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V value() {
        return getValue.get();
    }

    @Override
    public T root() {
        return this.self();
    }

    public Set<? extends T> children() {
        return children;
    }

    @Override
    public Collection<? extends T> subtrees(final Object key) {
        final T entry = this.getEntry(key);
        return Functions.ifNonNull(entry, T::children);
    }

    @Override
    public MutableTree<K, V, ?> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableSubtree<K, V> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
