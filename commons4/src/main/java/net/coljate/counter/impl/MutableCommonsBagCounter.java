package net.coljate.counter.impl;

import java.util.Comparator;

import net.coljate.counter.MutableCounter;

/**
 *
 * @author Ollie
 */
public class MutableCommonsBagCounter<T>
        extends CommonsBagCounter<T>
        implements MutableCounter<T> {

    public static <T> MutableCommonsBagCounter<T> createHashCounter() {
        return new MutableCommonsBagCounter<>(new org.apache.commons.collections4.bag.HashBag<>());
    }

    public static <T extends Comparable<? super T>> MutableCommonsBagCounter<T> createTreeCounter() {
        return new MutableCommonsBagCounter<>(new org.apache.commons.collections4.bag.TreeBag<>());
    }

    public static <T> MutableCommonsBagCounter<T> createTreeCounter(final Comparator<? super T> comparator) {
        return new MutableCommonsBagCounter<>(new org.apache.commons.collections4.bag.TreeBag<>(comparator));
    }

    protected MutableCommonsBagCounter(final org.apache.commons.collections4.Bag<T> bag) {
        super(bag);
    }

    @Override
    public void set(final T element, final int count) {
        final int current = this.count(element);
        if (count > current) {
            bag.add(element, count - current);
        } else if (count < current) {
            bag.remove(element, current - count);
        }
    }

    @Override
    public int increment(final T element, final int amount) {
        bag.add(element, amount);
        return this.count(element);
    }

    @Override
    public int decrement(final T element, final int amount) {
        bag.remove(amount, amount);
        return this.count(element);
    }

    @Override
    public void clear() {
        bag.clear();
    }

}