package com.github.jumarko.algorithm1;

import static com.github.jumarko.algorithm1.ArrayInversions.countInversions;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

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
}