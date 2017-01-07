package net.coljate.collection;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.coljate.Container;
import net.coljate.IterableExtension;
import net.coljate.StreamExtension;
import net.coljate.collection.SortedCollection.SortingAlgorithm;
import net.coljate.collection.impl.EmptyCollection;
import net.coljate.collection.impl.UnmodifiableCollection;
import net.coljate.collection.impl.WrappedCollection;
import net.coljate.collection.lazy.LazyCollection;
import net.coljate.list.impl.ImmutableSortedArray;
import net.coljate.set.Set;
import net.coljate.set.lazy.LazySet;
import net.coljate.util.complexity.TimeComplexity;

/**
 * Some {@link Iterable} {@link Container} with a {@link #count count} of elements.
 *
 * @author ollie
 * @since 1.0
 */
public interface Collection<T> extends IterableExtension<T>, StreamExtension<T> {

    /**
     *
     * @return a mutable copy of this collection.
     */
    @Nonnull
    MutableCollection<T> mutableCopy();

    /**
     *
     * @return an immutable copy of this collection.
     */
    @Nonnull
    ImmutableCollection<T> immutableCopy();

    /**
     *
     * @return a mutable copy of this collection.
     */
    @Nonnull
    default java.util.Collection<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.ArrayList::new);
    }

    @Nonnull
    default <C extends java.util.Collection<? super T>> C mutableJavaCopy(@Nonnull final IntFunction<? extends C> createCollection) {
        final C collection = createCollection.apply(this.count());
        this.forEach(collection::add);
        return collection;
    }

    /**
     *
     * @return a new array containing the elements in this collection.
     */
    @Nonnull
    default Object[] arrayCopy() {
        final Object[] array = new Object[this.count()];
        int index = 0;
        for (final T element : this) {
            array[index++] = element;
        }
        return array;
    }

    /**
     *
     * @param array
     * @return an array containing all the elements in this collection. This will either be the original array if it has
     * sufficient capacity, or a new array.
     * @see java.util.Collection#toArray(T[])
     */
    @Nonnull
    default T[] arrayCopy(@Nonnull final T[] array) {
        final int c = this.count();
        @SuppressWarnings("unchecked") //Same as java.util.AbstractCollection
        final T[] into = c > array.length
                ? (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), c)
                : array;
        int index = 0;
        for (final T element : this) {
            into[index++] = element;
        }
        return into;
    }

    @TimeComplexity(computed = true)
    @Nonnull
    default SortedCollection<T> sortedCopy(@Nonnull final Comparator<? super T> comparator) {
        return this.sortedCopy(comparator, SortingAlgorithm.DEFAULT);
    }

    @TimeComplexity(computed = true)
    @Nonnull
    default SortedCollection<T> sortedCopy(@Nonnull final Comparator<? super T> comparator, @Nonnull final SortingAlgorithm sortingAlgorithm) {
        return ImmutableSortedArray.sort(this, comparator, sortingAlgorithm);
    }

    @Override
    @Nonnull
    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(this.iterator(), this.count(), Spliterator.SIZED);
    }

    /**
     * Lazily translate this collection.
     *
     * @param <L>
     * @param intoLazy
     * @return
     */
    default <L extends LazyCollection<?>> L lazily(final Function<Collection<T>, L> intoLazy) {
        return intoLazy.apply(this);
    }

    @Nonnull
    default <R> Collection<R> transform(final Function<? super T, ? extends R> transformation) {
        return this.lazily(LazyCollection.<T, R>transform(transformation));
    }

    @Nonnull
    default Collection<T> filter(@Nonnull final Predicate<? super T> predicate) {
        return this.lazily(LazyCollection.filter(predicate));
    }

    @Nonnull
    default Set<T> distinct() {
        return this.lazily(LazySet.transform());
    }

    static <T> Empty<T> of() {
        return EmptyCollection.instance();
    }

    static <T> Collection<T> of(final T element) {
        return Set.of(element);
    }

    static <T> Collection<T> viewOf(final java.util.Collection<? extends T> collection) {
        return new WrappedCollection<>(collection);
    }

    static <T> Collection<T> viewOf(final Collection<? extends T> collection) {
        return UnmodifiableCollection.viewOf(collection);
    }

}