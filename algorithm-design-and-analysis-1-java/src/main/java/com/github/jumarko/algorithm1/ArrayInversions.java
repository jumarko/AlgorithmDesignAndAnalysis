package com.github.jumarko.algorithm1;

import java.util.Arrays;

/**
 * Encapsulates an algorithm for counting array inversions as demonstrated
 * in week 1 - Part III: https://www.coursera.org/learn/algorithm-design-analysis/lecture/IUiUk/o-n-log-n-algorithm-for-counting-inversions-ii
 */
public class ArrayInversions {

    public static int countInversionsNaive(int[] numbers) {
        int inversionsCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    inversionsCount++;
                }
            }
        }
        return inversionsCount;
    }

    public static int countInversions(int[] numbers) {
        if (numbers.length < 2) {
            return 0;
        }

        int firstHalfSize = numbers.length / 2;
        final int secondHalfSize = numbers.length - firstHalfSize;

        int[] firstHalf = new int[firstHalfSize];
        int[] secondHalf = new int[secondHalfSize];

        System.arraycopy(numbers, 0, firstHalf, 0, firstHalfSize);
        System.arraycopy(numbers, firstHalfSize, secondHalf, 0, secondHalfSize);

        return countInversions(firstHalf) + countInversions(secondHalf);
    }

}
