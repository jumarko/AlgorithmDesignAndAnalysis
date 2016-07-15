package com.github.jumarko.algorithm1.week02;

import static java.nio.file.Files.readAllLines;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class QuickSortTest {

    @Test
    public void quickSortShortArray() {
        final int[] a = {5, 4, 6, 1, 7, 9, 8};
        final int comparisonsCount = QuickSort.sort(a);
        assertThat(a, is(new int[] {1, 4, 5, 6, 7, 8, 9}));
        // depends on the pivot strategy
        assertThat(comparisonsCount, is(11));
    }

    @Test
    public void quickSortAlreadySortedArray() {
        final int[] a = {1, 4, 5, 6, 7, 8, 9};
        final int comparisonsCount = QuickSort.sort(a);
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
    public void quickSortProgrammingAssignment2WithFirstPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(10000, "/QuickSort.txt", 162085,
                (a, start, end) -> QuickSort.sortChoosingFirstElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortProgrammingAssignment2WithLastPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(10000, "/QuickSort.txt", 164123,
                (a, start, end) -> QuickSort.sortChoosingLastElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortProgrammingAssignment2WithMedianOfThreePivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(10000, "/QuickSort.txt", 138382,
                (a, start, end) -> QuickSort.sortChoosingMedianOfThreeAsPivot(a, 0, end));
    }

    /**
     * https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/10.txt
     *   expected comparisons counts:
     *   - first: 25
     *   - last: 29
     *   - median: 21
     */
    @Test
    public void quickSortTestCaseWith10ElementsAndFirstPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(10, "/QuickSort_10.txt", 25,
                (a, start, end) -> QuickSort.sortChoosingFirstElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortTestCaseWith10ElementsAndLastPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(10, "/QuickSort_10.txt", 29,
                (a, start, end) -> QuickSort.sortChoosingLastElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortTestCaseWith10ElementsAndMedianOfThreePivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(10, "/QuickSort_10.txt", 21,
                (a, start, end) -> QuickSort.sortChoosingMedianOfThreeAsPivot(a, 0, end));
    }

    /**
     * https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/100.txt
     *   expected comparisons counts:
     *   - first: 25
     *   - last: 29
     *   - median: 21
     */
    @Test
    public void quickSortTestCaseWith100ElementsAndFirstPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(100, "/QuickSort_100.txt", 615,
                (a, start, end) -> QuickSort.sortChoosingFirstElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortTestCaseWith100ElementsAndLastPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(100, "/QuickSort_100.txt", 587,
                (a, start, end) -> QuickSort.sortChoosingLastElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortTestCaseWith100ElementsAndMedianOfThreePivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(100, "/QuickSort_100.txt", 518,
                (a, start, end) -> QuickSort.sortChoosingMedianOfThreeAsPivot(a, 0, end));
    }

    /**
     * https://dl.dropboxusercontent.com/u/20888180/AlgI_wk2_testcases/1000.txt
     *   expected comparisons counts:
     *   - first: 25
     *   - last: 29
     *   - median: 21
     */
    @Test
    public void quickSortTestCaseWith1000ElementsAndFirstPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(1000, "/QuickSort_1000.txt", 10297,
                (a, start, end) -> QuickSort.sortChoosingFirstElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortTestCaseWith1000ElementsAndLastPivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(1000, "/QuickSort_1000.txt", 10184,
                (a, start, end) -> QuickSort.sortChoosingLastElementAsPivot(a, 0, end));
    }

    @Test
    public void quickSortTestCaseWith1000ElementsAndMedianOfThreePivotStrategy() throws Exception {
        sortAndCheckArrayFromResource(1000, "/QuickSort_1000.txt", 8921,
                (a, start, end) -> QuickSort.sortChoosingMedianOfThreeAsPivot(a, 0, end));
    }



    private static void sortAndCheckArrayFromResource(int arrayLength, String resourceName,
                                                      int expectedComparisonsCount,
                                                      QuickSort.SortFunction sortFunction) {
        final int[] a;
        try {
            a = readArrayFromResource(arrayLength, resourceName);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        final int[] sortedA = Arrays.copyOf(a, a.length);
        Arrays.sort(sortedA);

        final int comparisonsCount = sortFunction.apply(a, 0, a.length - 1);

        assertThat(a, is(sortedA));
        assertThat(comparisonsCount, is(expectedComparisonsCount));
    }


    private static int[] readArrayFromResource(int arrayLength, String resourceName) throws IOException, URISyntaxException {
        final int[] a = new int[arrayLength];
        final List<String> numbers = readAllLines(Paths.get(QuickSortTest.class.getResource(resourceName).toURI()));
        if (numbers.size() != arrayLength) {
            throw new IllegalStateException("Unexpected number of items in input array - should be " + arrayLength +
                    ", but found: " + numbers.size());
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.valueOf(numbers.get(i));
        }
        return a;
    }

}