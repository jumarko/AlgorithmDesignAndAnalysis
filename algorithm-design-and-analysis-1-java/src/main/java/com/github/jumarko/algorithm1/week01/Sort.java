package com.github.jumarko.algorithm1.week01;

public class Sort {

    public static int[] selectionSort(int[] numbers) {
        if (numbers.length < 2) {
            return numbers;
        }

        int[] sortedNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            int currentMin = numbers[0];
            int currentMinIndex = 0;
            for (int j = 0; j < numbers.length - i; j++) {
                if (numbers[j] < currentMin) {
                    currentMin = numbers[j];
                    currentMinIndex = j;
                }
            }
            sortedNumbers[i] = currentMin;
            // shift all elements in original array for next iteration -> the result should be one element shorter
            // than the previous version
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (j >= currentMinIndex) {
                    numbers[j] = numbers[j+1];
                }
            }
        }

        return sortedNumbers;
    }
}
