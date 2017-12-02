package net.coljate.collection.sorting;

import net.coljate.util.Arrays;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

import java.util.Comparator;

public interface SortingAlgorithm {

    /**
     * Sort an array of elements according to the given comparator.
     */
    @TimeComplexity(bestCase = Complexity.LINEAR, worstCase = Complexity.QUADRATIC)
    <T> void sort(T[] array, Comparator<? super T> comparator);

    /**
     * Sort an array of naturally comparable elements.
     */
    @TimeComplexity(bestCase = Complexity.LINEAR, worstCase = Complexity.QUADRATIC)
    @SuppressWarnings("type.argument.type.incompatible")
    default <T extends Comparable<? super T>> void sort(final T[] array) {
        this.sort(array, Comparator.naturalOrder());
    }

    default void sort(final int[] array) {
        final Integer[] integers = Arrays.copyNativeArray(array);
        this.sort(integers);
        Arrays.writeNativeArray(integers, array);
    }

    default void sort(final double[] array) {
        final Double[] doubles = Arrays.copyNativeArray(array);
        this.sort(doubles);
        Arrays.writeNativeArray(doubles, array);
    }

    SortingAlgorithm JAVA_DEFAULT = new SortingAlgorithm() {

        @Override
        public <T> void sort(final T[] array, final Comparator<? super T> comparator) {
            java.util.Arrays.sort(array, comparator);
        }

        @Override
        public void sort(final int[] array) {
            java.util.Arrays.sort(array);
        }

        @Override
        public void sort(double[] array) {
            java.util.Arrays.sort(array);
        }
    };

    SortingAlgorithm MERGE_SORT = MergeSortAlgorithm.INSTANCE;

}
