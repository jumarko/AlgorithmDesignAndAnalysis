package com.github.jumarko.algorithm1.week01;


/**
 * Encapsulates an algorithm for counting array inversions as demonstrated
 * in week 1 - Part III: https://www.coursera.org/learn/algorithm-design-analysis/lecture/IUiUk/o-n-log-n-algorithm-for-counting-inversions-ii
 */
public class ArrayInversions {

    public static long countInversionsNaive(int[] numbers) {
        long inversionsCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    inversionsCount++;
                }
            }
        }
        return inversionsCount;
    }

    public static long countInversions(int[] numbers) {
        return countAllInversions(numbers).count;
    }

    private static InversionsResult countAllInversions(int[] numbers) {
        if (numbers.length < 2) {
            return new InversionsResult(0, numbers);
        }

        // split input into two arrays - Divide & Conquer
        int firstHalfSize = numbers.length / 2;
        final int secondHalfSize = numbers.length - firstHalfSize;

        int[] firstHalf = new int[firstHalfSize];
        int[] secondHalf = new int[secondHalfSize];

        System.arraycopy(numbers, 0, firstHalf, 0, firstHalfSize);
        System.arraycopy(numbers, firstHalfSize, secondHalf, 0, secondHalfSize);

        // recursively compute all inversions - the primitive case (single or zero element array) means no inversion
        // multi-elements array are split to the primitive cases and then,
        // the split inversions are counted when two arrays are being merged into the destination much like in case
        // of merge sort algorithm.
        // Notice that we have to propagate sorted "destination" array as a result from this function
        // so that it can be used in higher-level "merge".
        final InversionsResult firstHalfInversions = countAllInversions(firstHalf);
        final InversionsResult secondHalfInversions = countAllInversions(secondHalf);
        final InversionsResult splitInversions = countSplitInversions(firstHalfInversions.sortedItems,
                secondHalfInversions.sortedItems);

        return new InversionsResult(firstHalfInversions.count + secondHalfInversions.count + splitInversions.count,
                splitInversions.sortedItems);
    }

    private static InversionsResult countSplitInversions(int[] firstHalf, int[] secondHalf) {

        int splitInversionsCount = 0;
        int fIndex = 0;
        int sIndex = 0;
        int[] destination = new int[firstHalf.length + secondHalf.length];
        for (int i = 0; i < destination.length; i++) {
            if (fIndex < firstHalf.length && sIndex < secondHalf.length) {
                if (firstHalf[fIndex] <= secondHalf[sIndex]) {
                    destination[fIndex + sIndex] = firstHalf[fIndex];
                    fIndex++;
                } else {
                    // split inversion - count is determined by the number of elements
                    // in first half that have not been processed yet
                    splitInversionsCount += firstHalf.length - fIndex;
                    destination[fIndex + sIndex] = secondHalf[sIndex];
                    sIndex++;
                }
            } else if (fIndex < firstHalf.length) {
                destination[fIndex + sIndex] = firstHalf[fIndex];
                fIndex++;
            } else if (sIndex < secondHalf.length) {
                destination[fIndex + sIndex] = secondHalf[sIndex];
                sIndex++;
            }
        }
        return new InversionsResult(splitInversionsCount, destination);
    }

    private static class InversionsResult {
        final long count;
        final int[] sortedItems;

        private InversionsResult(long count, int[] sortedItems) {
            this.count = count;
            this.sortedItems = sortedItems;
        }
    }

}
