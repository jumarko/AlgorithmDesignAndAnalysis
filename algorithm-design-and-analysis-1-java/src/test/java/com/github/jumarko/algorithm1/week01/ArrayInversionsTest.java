package com.github.jumarko.algorithm1.week01;

import static com.github.jumarko.algorithm1.week01.ArrayInversions.countInversions;
import static com.github.jumarko.algorithm1.week01.ArrayInversions.countInversionsNaive;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayInversionsTest {

    @Test
    public void noInversions() {
        assertThat(countInversions(new int[] {1, 2, 3, 4, 5, 6}), is(0L));
    }

    @Test
    public void noInversionsEmptyArray() {
        assertThat(countInversions(new int[] {}), is(0L));
    }

    @Test
    public void noInversionsSingleElemArray() {
        assertThat(countInversions(new int[] {1}), is(0L));
    }

    @Test
    public void fewInversions() {
        assertThat(countInversions(new int[] {1, 3, 5, 2, 4, 6}), is(3L));
    }

    @Test
    public void moreInversions() {
        assertThat(countInversions(new int[] {5, 3, 1, 6, 4, 2}), is(9L));
    }

    @Test
    public void maxInversions() {
        assertThat(countInversions(new int[] {6, 5, 4, 3, 2, 1}), is(15L));
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
        final long naiveCount = countInversionsNaive(numbers);
        final long end = System.currentTimeMillis();
        System.out.println("Duration (naive algorithm): " + (end - start) + " ms.");

        final long count = countInversions(numbers);
        System.out.println("Duration (O(n log n) algorithm): " + (System.currentTimeMillis() - end) + " ms.");

        System.out.println("Inversions count: " + count);

        assertEquals("optimised algorithm should provide the same result as the naive one", naiveCount, count);

    }
}