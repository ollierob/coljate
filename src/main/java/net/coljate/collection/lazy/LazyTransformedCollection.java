package net.coljate.collection.lazy;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.util.Iterators;

/**
 *
 * @author Ollie
 */
public class LazyTransformedCollection<F, T>
        implements LazyCollection<T> {

    public static <F, T> Function<Collection<F>, ? extends LazyTransformedCollection<F, T>> transform(final Function<? super F, ? extends T> transform) {
        return (collection) -> new LazyTransformedCollection<>(collection, transform);
    }

    private final Collection<F> collection;
    private final Function<? super F, ? extends T> transformation;

    public LazyTransformedCollection(final Collection<F> collection, final Function<? super F, ? extends T> transformation) {
        this.collection = Objects.requireNonNull(collection);
        this.transformation = Objects.requireNonNull(transformation);
    }

    protected T transform(final F element) {
        return transformation.apply(element);
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.transform(collection.iterator(), this::transform);
    }

}
