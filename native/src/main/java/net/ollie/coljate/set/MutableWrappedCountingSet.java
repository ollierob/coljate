package net.ollie.coljate.set;

import java.util.Iterator;

import net.ollie.coljate.map.Map;
import net.ollie.coljate.map.MutableMap;
import net.ollie.coljate.set.mixin.CopiedToHashSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedCountingSet<T>
        extends AbstractSet<T>
        implements CountingSet<T>, MutableSet<T>, CopiedToHashSet<T> {

    public static <T> MutableWrappedCountingSet<T> viewOf(final MutableMap<T, Integer> count) {
        return new MutableWrappedCountingSet<>(count);
    }

    public static <T> MutableWrappedCountingSet<T> copyOf(final Map<T, Integer> count) {
        return viewOf(count.mutableCopy());
    }

    private final MutableMap<T, Integer> count;

    protected MutableWrappedCountingSet(final MutableMap<T, Integer> count) {
        this.count = count;
    }

    @Override
    public int count(final Object object) {
        return count.getOrDefault(object, () -> 0);
    }

    @Override
    public Set<T> unique() {
        return count.keys();
    }

    @Override
    public MutableWrappedCountingSet<T> mutableCopy() {
        return copyOf(count);
    }

    @Override
    public boolean isEmpty() {
        return count.isEmpty();
    }

    @Override
    public boolean add(final T key) {
        count.compute(key, (k, current) -> (current == null ? 0 : current) + 1);
        return true;
    }

    @Override
    public boolean removeOnce(final Object key) {
        throw new UnsupportedOperationException(); //TODO cast
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean removeAll(final Object element) {
        final Integer count = this.count.deleteKey(element);
        return count != null && count > 0;
    }

    @Override
    public void clear() {
        count.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return count.keys().iterator();
    }

    @Override
    public CountingSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
