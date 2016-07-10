package com.github.jumarko.algorithm1.week01;

import static com.github.jumarko.algorithm1.week01.Sort.insertionSort;
import static com.github.jumarko.algorithm1.week01.Sort.selectionSort;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SortTest {

    @Test
    public void selectionSortShortArray() {
        assertThat(
                selectionSort(new int[] {5, 4, 6, 1, 7, 9, 8}),
                is(new int[] {1, 4, 5, 6, 7, 8, 9}));

    }

    @Test
    public void selectionSortSingleElement() {
        assertThat(
                selectionSort(new int[] {5}),
                is(new int[] {5}));

    }

    @Test
    public void selectionSortEmptyArray() {
        assertThat(
                selectionSort(new int[] {}),
                is(new int[] {}));

    }
    
        @Test
    public void insertionSortShortArray() {
        assertThat(
                insertionSort(new int[] {5, 4, 6, 1, 7, 9, 8}),
                is(new int[] {1, 4, 5, 6, 7, 8, 9}));

    }

    @Test
    public void insertionSortSingleElement() {
        assertThat(
                insertionSort(new int[] {5}),
                is(new int[] {5}));

    }

    @Test
    public void insertionSortEmptyArray() {
        assertThat(
                insertionSort(new int[] {}),
                is(new int[] {}));

    }
}