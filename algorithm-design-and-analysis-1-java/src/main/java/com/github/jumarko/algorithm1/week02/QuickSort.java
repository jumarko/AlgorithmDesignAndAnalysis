package com.github.jumarko.algorithm1.week02;

/**
 * QuickSort implementation according to lecture videos.
 * Check https://www.coursera.org/learn/algorithm-design-analysis/lecture/Zt0Ti/quicksort-overview
 */
public class QuickSort {

    /**
     * Sorts given array of integers in place, thus modifying original array.
     */
    public static void sort(int[] a) {
        sortChoosingFirstElementAsPivot(a, 0, a.length - 1);
    }

    /**
     * Sorts array using the first element as a pivot (always).
     * Only elements within bounds [start; end] will be sorted - both indices are inclusive.
     *
     * @param a array of distinct numbers
     * @param start index of the first element of array that should be sorted by this recursive call
     * @param end index of the last element of array that should be sorted by this recursive call
     */
    private static void sortChoosingFirstElementAsPivot(int[] a, int start, int end) {
        if (end - start < 1) {
            // array of length smaller than two
            return;
        }

        final int pivotIndex = partition(a, start, end);

        // recursive calls
        // Note there's no Combine step - the array is already sorted when recursive calls return

        // sort all elements lower than pivot
        sortChoosingFirstElementAsPivot(a, start, pivotIndex - 1);

        // sort all elements greater than pivot
        sortChoosingFirstElementAsPivot(a, pivotIndex + 1, end);
    }

    private static void sortChoosingLastElementAsPivot(int[] a, int start, int end) {
        // TODO: implement me
    }

    private static void sortUsingMedianOfThreePivotRule(int[] a, int start, int end) {
        // TODO: implement me
    }

    /**
     * Partition given array in such a way that:
     * - all elements lower than pivot should be in the part of array to the left of pivot
     * - all elements greater than pivot should be in the part of array to the right of pivot.
     * - pivot is in between left and right part
     * 
     * The pivot is always considered to be the element of array at _start_ position.
     * 
     * The input array should have always at least two elements!
     * 
     * @return index indicating the position of pivot between left and right part of an array;
     *         elements a[0], ..., a[index-1] are lower than pivot
     *         element a[index] is a pivot
     *         elements a[index+1], ..., a[length-1] are greater than pivot
     */
    private static int partition(int[] a, int start, int end) {
        if (a.length < 2) {
            throw new IllegalArgumentException("Array of length < 2. This should never be partitioned, but returned " +
                    "immediatelly as already sorted!");
        }
        if (start >= end) {
            throw new IllegalArgumentException("Start index must be lower than end index");
        }

        int pivot = a[start];
        int i = start + 1;
        for (int j = start + 1; j <= end; j++) {
            if (a[j] < pivot) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
            }
        }

        // swap pivot (the first element of array) with the last element that is lower than pivot
        a[start] = a[i-1];
        a[i-1] = pivot;

        // return pivot's index
        return i - 1;
    }


}
