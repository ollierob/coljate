package net.coljate.tree.impl;

import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableNode;
import net.coljate.tree.ImmutableTree;

/**
 *
 * @author ollie
 */
public class SimpleImmutableTree<K, V, N extends ImmutableNode<K, V, N>>
        extends AbstractTree<K, V, N>
        implements ImmutableTree<K, V, N> {

    private final N root;

    public SimpleImmutableTree(final N root) {
        this.root = root;
    }

    @Override
    public N root() {
        return root;
    }

    @Override
    public SimpleImmutableTree<K, V, N> immutableCopy() {
        return this;
    }

    @Override
    public ImmutableTree<K, V, ?> with(final K key, final V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}