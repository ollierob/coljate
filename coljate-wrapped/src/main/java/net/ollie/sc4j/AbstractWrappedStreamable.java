package net.ollie.sc4j;

import java.util.Iterator;

import net.ollie.sc4j.access.Streamable;

/**
 * Delegates operations to some underlying {@link Streamable iterable}.
 *
 * @author Ollie
 * @see AbstractNativelyDelegatedFinite
 */
public abstract class AbstractWrappedStreamable<V>
        extends AbstractStreamable<V> {

    protected abstract Streamable<V> underlying();

    @Override
    public Stream<V, ? extends Streamable<V>> stream() {
        return this.underlying().stream();
    }

    @Override
    public boolean isEmpty() {
        return this.underlying().isEmpty();
    }

    @Override
    public boolean contains(final Object object) {
        return this.underlying().contains(object);
    }

    @Override
    public Object[] toRawArray() {
        return this.underlying().toRawArray();
    }

    @Override
    public V head() {
        return this.underlying().head();
    }

    @Override
    public Iterator<V> iterator() {
        return this.underlying().iterator();
    }

    @Override
    public String toString() {
        return this.underlying().toString();
    }

    @Override
    public String toString(final String separator) {
        return this.underlying().toString(separator);
    }

}
