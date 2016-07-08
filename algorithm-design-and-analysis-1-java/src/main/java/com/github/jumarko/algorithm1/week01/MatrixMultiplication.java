package com.github.jumarko.algorithm1.week01;

/**
 * Demonstrates two algorithms for matrix multiplication
 * -- naive with running time O(n^3)
 * -- Strassen's with running strictly less than O(n^3) using a trick with productions reduction from 8 to 7.
 *
 * Matrix is represented by two dimensional array.
 */
public class MatrixMultiplication {

    /**
     * Naive multiplication algorithm corresponding to the "school" algorithm:
     * A x B = C, where c_ij is a dot product of vectors i-th row of A and j-th column of B.
     */
    public static int[][] multiplyNaive(int[][] a, int[][] b) {
        checkDimensions(a, b);
        if (a.length == 0) {
            return new int[0][0];
        }

        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int dotProduct = 0;
                for (int k = 0; k < a.length; k++) {
                    dotProduct += a[i][k] * b[k][j];
                }
                result[i][j] = dotProduct;
            }
        }

        return result;
    }


    /**
     * Optimized Strassen's divide-and-conquer algorithm for multiplication of matrices.
     * The number of necessary products is reduced from 8 to 7 by using clever products and substraction and addition
     * between them.
     */
    public static int[][] multiplyStrassen(int[][] a, int[][] b) {
        // TODO: implement
        return null;
    }


    private static void checkDimensions(int[][] a, int[][] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Matrices must have the same number of rows: a.length=" + a.length +
                    ", but b.length=" + b.length);
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) {
                throw new IllegalArgumentException(String.format("Each row must have have the same number of columns in both matrices:" +
                        " a[%s].length=%s, but b[%s].length=%s", i, a[i].length, i, b[i].length));
            }
        }
    }
}
