package net.coljate.list;

import net.coljate.feature.Ordered;
import net.coljate.list.impl.ImmutableNativeArray;
import net.coljate.list.impl.MutableWrappedList;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 */
public interface List<T> extends Ordered<T> {

    @Override
    ListIterator<T> iterator();

    @Override
    default T first() {
        return this.iterator().next();
    }

    T last();

    @Override
    default MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

    @Override
    default java.util.List<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.ArrayList::new);
    }

    default boolean elementsEqual(final List<?> that) {
        return Equality.orderedEquals(this, that);
    }

    @SafeVarargs
    static <T> List<T> copyOf(final T... elements) {
        return ImmutableList.copyOf(elements);
    }

    static <T> MutableWrappedList<T> viewOf(final java.util.List<T> list) {
        return MutableWrappedList.viewOf(list);
    }

}
