package net.ollie.coljate.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.list.mixin.WrapsImmutableList;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public class ImmutableAppendList<@Nullable T>
        extends AbstractList<T>
        implements WrapsImmutableList<T> {

    public static <T> ImmutableList<T> of(@NonNull final ImmutableList<? extends T> list, @Nullable final T right) {
        return list.isEmpty()
                ? ImmutableWrappedArrayList.of(right)
                : new ImmutableAppendList<>(list, right);
    }

    private final ImmutableList<? extends T> left;
    private final T right;

    ImmutableAppendList(final ImmutableList<? extends T> left, final T right) {
        this.left = requireNonNull(left);
        this.right = right;
    }

    @Override
    public ImmutableList<T> tail() {
        return left.isEmpty()
                ? ImmutableWrappedEmptyList.empty()
                : of(left.tail(), right);
    }

    @Override
    public T get(final int index) {
        return index == left.count()
                ? right
                : left.get(index);
    }

    @Override
    public int count() {
        return left.count() + 1;
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new UnmodifiableIterator<T>() {

            final Iterator<? extends T> iterator = left.iterator();
            boolean finished = false;

            @Override
            public boolean hasNext() {
                return iterator.hasNext() || finished;
            }

            @Override
            public T next() {
                if (finished) {
                    throw new NoSuchElementException();
                }
                if (iterator.hasNext()) {
                    return iterator.next();
                }
                finished = true;
                return right;
            }

        };
    }

}