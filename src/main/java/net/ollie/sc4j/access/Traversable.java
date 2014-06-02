package net.ollie.sc4j.access;

import java.util.function.Predicate;

import net.ollie.sc4j.Collection;

/**
 *
 * @author Ollie
 */
public interface Traversable<V>
        extends Collection<V> {

    V head();

    Traversable<V> tail();

    @Override
    default V findOrElse(Predicate<? super V> predicate, V defaultValue) {
        Traversable<V> tail = this;
        V head;
        while ((head = tail.head()) != null) {
            if (predicate.test(head)) {
                return head;
            }
            tail = tail.tail();
        }
        return defaultValue;
    }

    @Override
    Traversable.Mutable<V> mutableCopy();

    @Override
    Traversable.Immutable<V> immutableCopy();

    interface Mutable<V>
            extends Traversable<V>, Collection.Mutable<V> {

    }

    interface Immutable<V>
            extends Traversable<V>, Collection.Immutable<V> {

        @Override
        Traversable.Immutable<V> tail();

        @Override
        default Traversable.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends Collection.Empty<V>, Traversable.Immutable<V> {

        @Override
        default V head() {
            return null;
        }

        @Override
        default Traversable.Empty<V> tail() {
            return this;
        }

        @Override
        default V findOrElse(final Predicate<? super V> predicate, V defaultValue) {
            return Collection.Empty.super.findOrElse(predicate, defaultValue);
        }

    }

}
