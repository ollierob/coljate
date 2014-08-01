package net.ollie.sc4j.imposed;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.utils.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 * Values are not unique, in that they can exist within this collection any number of times.
 *
 * @author Ollie
 * @see Unique
 */
public interface Duplicated<V>
        extends Collection<V>, Keyed.Single<V, NonNegativeInteger> {

    int count(Object value);

    @Override
    @Nonnull
    default NonNegativeInteger get(final Object value) {
        return NonNegativeInteger.of(this.count(value));
    }

    @Override
    default boolean isEmpty() {
        return Keyed.Single.super.isEmpty();
    }

}
