package net.coljate.graph;

import net.coljate.collection.ImmutableCollection;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public interface ImmutableGraph<V, E>
        extends Graph<V, E>, ImmutableSet<Relationship<V, E>> {

    @Override
    default ImmutableSet<V> vertices() {
        return Graph.super.vertices().immutableCopy();
    }

    @Override
    default ImmutableCollection<E> edges() {
        return Graph.super.edges().immutableCopy();
    }

    @Override
    @Deprecated
    default ImmutableGraph<V, E> immutableCopy() {
        return this;
    }

}