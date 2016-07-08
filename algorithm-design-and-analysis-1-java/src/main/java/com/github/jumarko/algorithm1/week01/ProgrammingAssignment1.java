package com.github.jumarko.algorithm1.week01;

import static com.github.jumarko.algorithm1.week01.ArrayInversions.countInversions;
import static com.github.jumarko.algorithm1.week01.ArrayInversions.countInversionsNaive;
import static java.nio.file.Files.readAllLines;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Solution of programming assignment from week 1: https://www.coursera.org/learn/algorithm-design-analysis/exam/YLbzP/programming-assignment-1
 *
 * Given array of integers from 1 to 100 000
 *   (check /Users/jumar/Private/edu/courses/coursera/AlgorithmsDesignAndAnalysis-Part1/ProgrammingAssignment1_IntegerArray.txt)
 * You have to find number of inversions.
 */
public class ProgrammingAssignment1 {


    public static void main(String[] args) throws IOException {
        final List<String> numberLines = readAllLines(
                Paths.get("/Users/jumar/Private/edu/courses/coursera/AlgorithmsDesignAndAnalysis-Part1",
                "ProgrammingAssignment1_IntegerArray.txt"));
        assert 100000 == numberLines.size();
        final int[] numbers = new int[numberLines.size()];
        int i = 0;
        for (String numberStr : numberLines) {
            numbers[i] = Integer.parseInt(numberStr.trim());
            i++;
        }
        final long start = System.currentTimeMillis();
        final long naiveCount = countInversionsNaive(numbers);
        final long end = System.currentTimeMillis();
        System.out.println("Duration (naive algorithm): " + (end - start) + " ms.");

        final long count = countInversions(numbers);
        System.out.println("Duration (O(n log n) algorithm): " + (System.currentTimeMillis() - end) + " ms.");

        assert naiveCount == count: "optimised algorithm should provide the same result as the naive one";
        System.out.println();
        System.out.println("Number of inversions found: " + count);
    }

}
