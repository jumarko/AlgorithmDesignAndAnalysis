package com.github.jumarko.algorithm1.week01;

import static com.github.jumarko.algorithm1.week01.MatrixMultiplication.multiplyNaive;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixMultiplicationTest {
    private static final int[][] EMPTY_MATRIX = new int[0][0];


    @Test
    public void multiplyEmptyMatrices() {
        assertThat(multiplyNaive(EMPTY_MATRIX, EMPTY_MATRIX), is(EMPTY_MATRIX));
    }

    @Test
    public void multiply2By2Matrices() {
        assertThat(multiplyNaive(
                new int[][] {new int[] {1, 2}, new int[] {3, 4}},
                new int[][] {new int[] {5, 6}, new int[] {7, 8}}),
                is(new int[][] {new int[] {19, 22}, new int[] {43, 50}})
        );
    }

    @Test
    public void multiply3By3Matrices() {
        assertThat(multiplyNaive(
                new int[][] {new int[] {1, 2, 3}, new int[] {7, 8, 9}, new int[] {4, 5, 6}},
                new int[][] {new int[] {1, 0, 3}, new int[] {1, 8, 4}, new int[] {5, 6, 7}}),
                is(new int[][] {new int[] {18, 34, 32}, new int[] {60, 118, 116}, new int[] {39, 76, 74}})
        );
    }
}