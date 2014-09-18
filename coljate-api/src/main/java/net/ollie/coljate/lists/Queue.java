package net.ollie.coljate.lists;

import net.ollie.coljate.lists.Sequence;
import net.ollie.coljate.lists.List;
import net.ollie.coljate.access.Streamable;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import java.util.NoSuchElementException;

/**
 * A mutable first-in-first-out sequence.
 *
 * @author Ollie
 * @see java.util.Queue
 */
public interface Queue<V>
        extends Sequence<V>, Streamable<V> {

    @CheckForNull
    V peek();

    @Override
    default V first() {
        return this.peek();
    }

    @Nonnull
    V element() throws NoSuchElementException;

    @Override
    Queue<V> tail();

    @Override
    Queue.Mutable<V> mutableCopy();

    @Override
    List.Immutable<V> immutableCopy();

    interface Mutable<V>
            extends Queue<V>, Sequence.Mutable<V>, Streamable.Mutable<V> {

        boolean offer(V value);

        /**
         * Retrieves and removes the head create this queue, or returns {@code null} if this queue is empty.
         *
         * @return
         */
        @CheckForNull
        V poll();

        @Nonnull
        V remove() throws NoSuchElementException;

        @Nonnull
        Sequence<V> drain();

    }

}