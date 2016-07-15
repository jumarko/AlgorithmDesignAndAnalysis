package com.github.jumarko.algorithm1.week02;

import java.util.Arrays;

/**
 * QuickSort implementation according to lecture videos.
 * Check https://www.coursera.org/learn/algorithm-design-analysis/lecture/Zt0Ti/quicksort-overview
 *
 * Some test cases can also be found at: https://www.coursera.org/learn/algorithm-design-analysis/discussions/weeks/2/threads/lKD8kzJBEeaHEgowKm82Aw
 *
 * - https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/10.txt
 *   expected comparisons counts:
 *   - first: 25
 *   - last: 29
 *   - median: 21
 *
 * - https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/100.txt
 *   expected comparisons counts:
 *   - first: 615
 *   - last: 587
 *   - median: 518
 *
 * - https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/1000.txt
 *   expected comparisons counts:
 *   - first: 10297
 *   - last: 10184
 *   - median: 8921
 *
 * Check unit tests!
 */
public class QuickSort {

    /**
     * Sorts given array of integers in place, thus modifying original array.
     * @return total number of comparisons made by this quicksort implementation
     */
    public static int sort(int[] a) {
//        return sortChoosingFirstElementAsPivot(a, 0, a.length - 1);
//        return sortChoosingLastElementAsPivot(a, 0, a.length - 1);
        return sortChoosingMedianOfThreeAsPivot(a, 0, a.length - 1);
    }

    /**
     * Sorts array using the first element as a pivot (always).
     * Only elements within bounds [start; end] will be sorted - both indices are inclusive.
     *
     * @param a array of distinct numbers
     * @param start index of the first element of array that should be sorted by this recursive call
     * @param end index of the last element of array that should be sorted by this recursive call
     *
     * @return total number of comparisons made by this quicksort implementation
     */
    static int sortChoosingFirstElementAsPivot(int[] a, int start, int end) {
        return sortUsingPivotStrategy(a, start, end,
                QuickSort::sortChoosingFirstElementAsPivot,
                // since pivot is already at its place, we don't need to swap anything
                (a1, start1, end1) -> 0);
    }

    static int sortChoosingLastElementAsPivot(int[] a, int start, int end) {
        return sortUsingPivotStrategy(a, start, end,
                QuickSort::sortChoosingLastElementAsPivot,
                QuickSort::useLastElementAsPivot);
    }

    private static int useLastElementAsPivot(int[] a, int start, int end) {
        int lastElement = a[end];
        a[end] = a[start];
        a[start] = lastElement;

        // doesn't mean anything -> just to satisfy interface
        return 0;
    }

    static int sortChoosingMedianOfThreeAsPivot(int[] a, int start, int end) {
        return sortUsingPivotStrategy(a, start, end,
                QuickSort::sortChoosingMedianOfThreeAsPivot,
                QuickSort::useMedianOfThreeAsPivot);
    }

    private static int useMedianOfThreeAsPivot(int[] a, int start, int end) {
        int first = a[start];
        int last = a[end];
        final int middleIndex = start + ((end - start) / 2);
        int middle = a[middleIndex];

        int[] medianArray = new int[3];
        medianArray[0] = first;
        medianArray[1] = middle;
        medianArray[2] = last;
        Arrays.sort(medianArray);

        if (medianArray[1] == first) {
            // the first element is already a pivot, don't need to do anything
        } else if (medianArray[1] == middle) {
            // the middle element is pivot, swap it with first element
            a[start] = middle;
            a[middleIndex] = first;
        } else if (medianArray[1] == last) {
            // the last element is pivot, swap it with first element
            a[start] = last;
            a[end] = first;
        }

        // doesn't mean anything -> just to satisfy interface
        return 0;
    }



    private static int sortUsingPivotStrategy(int[] a, int start, int end, SortFunction sortFunction,
                                              SortFunction swapPivotWithFirstElementFunction) {
        if (end - start < 1) {
            // array of length smaller than two
            return 0;
        }

        int totalComparisonsCount = end - start;

        swapPivotWithFirstElementFunction.apply(a, start, end);


        final int pivotIndex = partition(a, start, end);


        // recursive calls
        // Note there's no Combine step - the array is already sorted when recursive calls return

        // sort all elements lower than pivot
        totalComparisonsCount += sortFunction.apply(a, start, pivotIndex - 1);


        // sort all elements greater than pivot
        totalComparisonsCount += sortFunction.apply(a, pivotIndex + 1, end);

        return totalComparisonsCount;
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


    interface SortFunction {
        int apply(int[] array, int start, int end);
    }

}
