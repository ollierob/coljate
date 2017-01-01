package net.coljate.graph;

import java.util.Objects;
import java.util.Optional;

import net.coljate.set.impl.TwoSet;

/**
 * Encapsulates an edge between two vertices.
 *
 * The vertices are reversible if this is a member of an undirected graph.
 *
 * @param <V> vertex type
 * @param <E> edge type
 */
public interface Relationship<V, E> {

    E edge();

    TwoSet<V> vertices();

    default boolean containsVertex(final Object vertex) {
        return this.vertices().contains(vertex);
    }

    boolean isBetween(Object fromVertex, Object toVertex);

    default Optional<V> otherVertex(final Object vertex) {
        final TwoSet<V> vertices = this.vertices();
        if (Objects.equals(vertex, vertices.first())) {
            return Optional.of(vertices.second());
        } else if (Objects.equals(vertex, vertices.second())) {
            return Optional.of(vertices.first());
        } else {
            return Optional.empty();
        }
    }
    
}