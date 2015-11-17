package net.ollie.coljate;

import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface GenericMutableWrappedCollection<@Nullable T> extends MutableCollection<T> {

    @Nonnull
    java.util.Collection<T> delegate();

    @Override
    default boolean add(final T element) {
        return this.delegate().add(element);
    }

    @Override
    default boolean addAll(final Iterable<? extends T> iterable) {
        return iterable instanceof java.util.Collection
                ? this.delegate().addAll((java.util.Collection<? extends T>) iterable)
                : MutableCollection.super.addAll(iterable);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    default boolean remove(final Object element) {
        return this.delegate().remove(element);
    }

    @Override
    default boolean removeAll(final Iterable<? extends T> iterable) {
        return iterable instanceof java.util.Collection
                ? this.delegate().removeAll((java.util.Collection<? extends T>) iterable)
                : MutableCollection.super.removeAll(iterable);
    }

    @Override
    default boolean clear() {
        final boolean any = !this.delegate().isEmpty();
        this.delegate().clear();
        return any;
    }

}
