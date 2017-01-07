package net.coljate.tree.impl;

import java.util.function.Supplier;

import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.set.Set;
import net.coljate.tree.AbstractTree;
import net.coljate.tree.MutableTree;
import net.coljate.tree.Node;
import net.coljate.tree.Tree;
import net.coljate.util.SelfTyped;

/**
 *
 * @author ollie
 */
public class Subtree<K, V, T extends Subtree<K, V, T>>
        extends AbstractTree<K, V, T>
        implements Tree<K, V, T>, Node<K, V, T>, SelfTyped<T> {

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

    public Entry<K, V> asEntry() {
        return ImmutableEntry.of(this.key(), this.value());
    }

    @Override
    public T root() {
        return this.self();
    }

    @Override
    public Set<? extends T> children() {
        return children;
    }

    @Override
    public boolean contains(final Object key, final Object value) {
        return Node.super.contains(key, value);
    }

    @Override
    public MutableTree<K, V, ?> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableSubtree<K, V> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(final Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Subtrees cannot be hashed.");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":[" + this.key() + ":" + this.value() + "]:" + children;
    }

}