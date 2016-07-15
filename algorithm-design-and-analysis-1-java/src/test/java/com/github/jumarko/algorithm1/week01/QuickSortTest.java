package com.github.jumarko.algorithm1.week01;

import static java.nio.file.Files.readAllLines;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.github.jumarko.algorithm1.week02.QuickSort;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class QuickSortTest {

    @Test
    public void quickSortShortArray() {
        final int[] a = {5, 4, 6, 1, 7, 9, 8};
        QuickSort.sort(a);
        assertThat(a, is(new int[] {1, 4, 5, 6, 7, 8, 9}));

    }

    @Test
    public void quickSortSingleElement() {
        final int[] a = {5};
        QuickSort.sort(a);
        assertThat(a, is(new int[] {5}));
    }

    @Test
    public void quickSortEmptyArray() {
        final int[] a = new int[0];
        QuickSort.sort(a);
        assertThat(a, is(new int[0]));
    }

    @Test
    public void quickSortProgrammingAssignment2() throws Exception {
        final int[] a = new int[10000];
        final List<String> numbers = readAllLines(Paths.get(getClass().getResource("/QuickSort.txt").toURI()));
        if (numbers.size() != a.length) {
            throw new IllegalStateException("Unexpected number of items in input array - should be 10,000, but found: "
                + numbers.size());
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.valueOf(numbers.get(i));
        }
        QuickSort.sort(a);
        assertThat(a, is(new int[0]));
    }
    
}