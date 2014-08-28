package net.ollie.sc4j;

import net.ollie.sc4j.imposed.sorting.Sorted;
import net.ollie.sc4j.imposed.sorting.Sorter;
import net.ollie.sc4j.utils.Iterables;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import java.util.Comparator;

/**
 * @author Ollie
 */
public interface SortedSet<V>
        extends Set<V>, Interval<V>, Sorted<V> {

    @Override
    V last();

    @Override
    default boolean firstInclusive() {
        return true;
    }

    @Override
    default boolean lastInclusive() {
        return true;
    }

    @Override
    SortedSet.Mutable<V> mutableCopy();

    @Override
    SortedSet.Immutable<V> immutableCopy();

    default boolean equals(final SortedSet<?> that) {
        return that != null
                && this.count() == that.count()
                && Iterables.equals(this, that);
    }

    @Override
    default int hash() {
        return Iterables.productHashCode(this);
    }

    interface Mutable<V>
            extends SortedSet<V>, Set.Mutable<V> {

        void sort(Comparator<? super V> comparator);

    }

    interface Immutable<V>
            extends SortedSet<V>, Set.Immutable<V> {

        @Override
        SortedSet.Immutable<V> tail();

        @Nonnull
        @CheckReturnValue
        SortedSet.Immutable<V> sort(Sorter<? super V> comparator);

        @Override
        default SortedSet.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends SortedSet.Immutable<V>, Set.Empty<V> {

        default V first() {
            return null;
        }

        @Override
        default V last() {
            return null;
        }

        @Override
        default SortedSet.Empty<V> tail() {
            return this;
        }

        @Override
        default SortedSet.Empty<V> sort(final Sorter<? super V> comparator) {
            return this;
        }

    }

    interface Singleton<V>
            extends SortedSet.Immutable<V>, Set.Singleton<V> {

        @Override
        SortedSet.Empty<V> tail();

    }

}
