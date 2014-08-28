package net.ollie.sc4j;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.imposed.Distinctness.Duplicated;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

/**
 *
 * @author Ollie
 * @see Set
 */
public interface MultiSet<V>
        extends Finite<V>, Keyed.Single<V, NonNegativeInteger>, Duplicated<V> {

    @Override
    default NonNegativeInteger get(final Object object) {
        return this.count(object);
    }

    @Override
    Set<V> unique();

    @Override
    Set<V> keys();

    @Override
    Finite<NonNegativeInteger> values();

    @Override
    default boolean isEmpty() {
        return Keyed.Single.super.isEmpty();
    }

    @Override
    MultiSet.Mutable<V> mutableCopy();

    @Override
    MultiSet.Immutable<V> immutableCopy();

    default boolean equals(final MultiSet<?> that) {
        return that != null
                && this.keys().equals(that.keys())
                && this.values().equals(that.values());
    }

    @Override
    default int hash() {
        return this.keys().hashCode() + this.values().hashCode();
    }

    interface Mutable<V>
            extends MultiSet<V>, Finite.Mutable<V> {

        /**
         * Increment the given value by 1. If the value is not present, it is added and 1 is returned.
         *
         * @param value
         * @return the new value.
         */
        int increment(V value);

        int decrement(Object value);

        default boolean add(final V value) {
            return this.increment(value) == 1;
        }

        default boolean remove(final Object value) {
            return this.decrement(value) == 0;
        }

    }

    interface Immutable<V>
            extends MultiSet<V>, Finite.Immutable<V> {

        @Override
        default MultiSet.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
