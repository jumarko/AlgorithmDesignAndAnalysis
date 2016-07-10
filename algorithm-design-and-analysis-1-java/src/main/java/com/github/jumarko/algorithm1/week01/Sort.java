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

    public static int[] insertionSort(int[] numbers) {
        if (numbers.length < 2) {
            return numbers;
        }
        for (int i = 1; i < numbers.length; i++) {
            int current = numbers[i];
            // check all previous elements (which should be partially sorted) and continue swapping
            // while current is smaller than previous element
            int j = i - 1;
            int previous = numbers[j];
            while (current < previous) {
                numbers[j] = current;
                numbers[j+1] = previous;
                j--;
                if (j < 0) {
                    break;
                }
                previous = numbers[j];
            }
        }
        return numbers;
    }
}
