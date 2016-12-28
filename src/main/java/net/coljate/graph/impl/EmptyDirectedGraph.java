package net.coljate.graph.impl;

import net.coljate.graph.DirectedRelationship;
import net.coljate.graph.ImmutableDirectedGraph;
import net.coljate.graph.Relationship;
import net.coljate.util.iterator.UnmodifiableCovariantIterator;

/**
 *
 * @author ollie
 */
public class EmptyDirectedGraph<V, E>
        extends EmptyGraph<V, E>
        implements ImmutableDirectedGraph<V, E> {

    private static final long serialVersionUID = 1L;

    private static final EmptyDirectedGraph INSTANCE = new EmptyDirectedGraph();

    public static <V, E> EmptyDirectedGraph<V, E> instance() {
        return INSTANCE;
    }

    @Override
    public UnmodifiableCovariantIterator<Relationship<V, E>, ? extends DirectedRelationship<V, E>> iterator() {
        return UnmodifiableCovariantIterator.of();
    }

    @Override
    public EmptyDirectedGraph<V, E> immutableCopy() {
        return this;
    }

}
