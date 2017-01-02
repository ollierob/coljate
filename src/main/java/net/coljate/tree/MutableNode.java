package net.coljate.tree;

import net.coljate.map.MutableEntry;

/**
 *
 * @author ollie
 */
public interface MutableNode<K, V, N extends MutableNode<K, V, N>>
        extends Node<K, V, N>, MutableEntry<K, V> {

}
