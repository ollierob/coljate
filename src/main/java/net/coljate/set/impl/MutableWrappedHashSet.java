package net.coljate.set.impl;

import java.io.Serializable;

/**
 *
 * @author ollie
 */
public class MutableWrappedHashSet<T>
        extends MutableWrappedSet<T>
        implements Serializable {

    public static <T> MutableWrappedHashSet<T> viewOf(final java.util.HashSet<T> set) {
        return new MutableWrappedHashSet<>(set);
    }

    public static <T> MutableWrappedHashSet<T> copyOf(final java.util.Set<T> set) {
        return new MutableWrappedHashSet<>(new java.util.HashSet<>(set));
    }

    private static final long serialVersionUID = 1L;

    private final java.util.HashSet<T> delegate;

    protected MutableWrappedHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    protected java.util.HashSet<T> mutableDelegateCopy() {
        return new java.util.HashSet<>(delegate);
    }

    @Override
    public MutableWrappedHashSet<T> mutableCopy() {
        return new MutableWrappedHashSet<>(this.mutableDelegateCopy());
    }

}
