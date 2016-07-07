package com.github.jumarko.algorithm1;

import static com.github.jumarko.algorithm1.ArrayInversions.countInversions;
import static com.github.jumarko.algorithm1.ArrayInversions.countInversionsNaive;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArrayInversionsTest {

    @Test
    public void noInversions() {
        assertThat(countInversions(new int[] {1, 2, 3, 4, 5, 6}), is(0));
    }

    @Test
    public void noInversionsEmptyArray() {
        assertThat(countInversions(new int[] {}), is(0));
    }

    @Test
    public void noInversionsSingleElemArray() {
        assertThat(countInversions(new int[] {1}), is(0));
    }

    @Test
    public void fewInversions() {
        assertThat(countInversions(new int[] {1, 3, 5, 2, 4, 6}), is(3));
    }

    @Test
    public void moreInversions() {
        assertThat(countInversions(new int[] {5, 3, 1, 6, 4, 2}), is(9));
    }

    @Test
    public void maxInversions() {
        assertThat(countInversions(new int[] {6, 5, 4, 3, 2, 1}), is(15));
    }

    @Test
    public void veryManyInversions() {
        // notice that even for 1M elements array the naive algorithm takes quite long
        // for 128K array it takes ~20 seconds to complete
        int arraySize = 1024 * 128;
        final List<Integer> numbersList = new ArrayList<Integer>(arraySize);
        for (int i = 0; i < arraySize; i++) {
            numbersList.add(i);
        }
        Collections.shuffle(numbersList);

        final int[] numbers = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = numbersList.get(i);
        }

        final long start = System.currentTimeMillis();
        final int naiveCount = countInversionsNaive(numbers);
        final long end = System.currentTimeMillis();
        System.out.println("Duration (naive algorithm): " + (end - start) + " ms.");

        final int count = countInversions(numbers);
        System.out.println("Duration (O(n log n) algorithm): " + (System.currentTimeMillis() - end) + " ms.");

        assertEquals("optimised algorithm should provide the same result as the naive one", naiveCount, count);
    }
}