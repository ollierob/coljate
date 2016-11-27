package net.coljate;

import java.util.Iterator;

import net.coljate.utils.Equality;

/**
 *
 * @author ollie
 */
public class WrappedCollection<T> implements Collection<T> {

    public static <T> Collection<T> viewOf(final java.util.Collection<T> collection) {
        return new WrappedCollection<>(collection);
    }

    private final java.util.Collection<T> delegate;

    protected WrappedCollection(final java.util.Collection<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public int count() {
        return delegate.size();
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    protected java.util.Collection<T> mutableDelegateCopy() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public MutableCollection<T> mutableCopy() {
        return new MutableWrappedCollection<>(this.mutableDelegateCopy());
    }

    @Override
    public ImmutableCollection<T> immutableCopy() {
        return ImmutableWrappedCollection.copyOf(delegate);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Collection
                && Equality.orderedEquals(this, (Collection) obj);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException(); //TODO
    }

}
