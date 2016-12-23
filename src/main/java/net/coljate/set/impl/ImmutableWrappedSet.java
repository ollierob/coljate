package net.coljate.set.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.Collection;
import net.coljate.set.ImmutableSet;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
public class ImmutableWrappedSet<T>
        extends WrappedSet<T>
        implements ImmutableSet<T> {

    @SafeVarargs
    public static <T> ImmutableSet<T> copyIntoHashSet(final T... elements) {
        final java.util.Set<T> set = new java.util.HashSet<>(elements.length);
        Arrays.consume(elements, set::add);
        return new ImmutableWrappedSet<>(set);
    }

    public static <T> ImmutableSet<T> copyIntoHashSet(final Collection<? extends T> collection) {
        final java.util.Set<T> set = collection.mutableJavaCopy(i -> new java.util.HashSet<>(i));
        return new ImmutableWrappedSet<>(set);
    }

    protected ImmutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
