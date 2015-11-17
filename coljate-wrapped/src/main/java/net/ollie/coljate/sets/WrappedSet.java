package net.ollie.coljate.sets;

import static java.util.Objects.requireNonNull;

import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.sets.mixin.WrapsSet;

/**
 *
 * @author Ollie
 */
public class WrappedSet<T>
        extends WrappedCollection<T>
        implements WrapsSet<T> {

    private final java.util.Set<T> delegate;

    public WrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    protected java.util.Set<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.Set<T> copyDelegate() {
        return new java.util.HashSet<>(delegate);
    }

    @Override
    public Set<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableSet<T> mutableCopy() {
        return MutableWrappedHashSet.copyOf(delegate);
    }

    @Override
    public ImmutableSet<T> immutableCopy() {
        return ImmutableWrappedHashSet.copyOf(delegate);
    }

}
