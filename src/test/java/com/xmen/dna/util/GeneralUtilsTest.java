package com.xmen.dna.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GeneralUtilsTest {

    @Test
    public void test_getStringFromStringArray_ok() {
        String transformedFromArray = GeneralUtils.getStringFromStringArray(new String[] {"aaaaa", "ccccc", "ddddd"});
        assertEquals("aaaaacccccddddd",transformedFromArray);
    }

    @Test
    public void test_getStringFromStringArray_null_array() {
        String transformedFromArray = GeneralUtils.getStringFromStringArray(null);
        assertEquals("",transformedFromArray);
    }

    @Test
    public void test_getStringFromStringArray_empty_array() {
        String transformedFromArray = GeneralUtils.getStringFromStringArray(null);
        assertEquals("",transformedFromArray);
    }

    @Test
    public void test_calculateRatio_ok() {
        Float calculatedRatio = GeneralUtils.calculateRatio(33L,100L);
        assertEquals(0.33F,calculatedRatio,0);
    }

    @Test
    public void test_calculateRatio_secundary_0() {
        Float calculatedRatio = GeneralUtils.calculateRatio(33L,0L);
        assertEquals(33F,calculatedRatio,0);
    }

    @Test
    public void test_calculateRatio_principal_0_secundary_0() {
        Float calculatedRatio = GeneralUtils.calculateRatio(0L,0L);
        assertEquals(0F,calculatedRatio,0);
    }

    @Test
    public void test_calculateRatio_principal_0() {
        Float calculatedRatio = GeneralUtils.calculateRatio(0L,0L);
        assertEquals(0F,calculatedRatio,0);
    }

    @Test
    public void test_calculateRatio_principal_null() {
        Float calculatedRatio = GeneralUtils.calculateRatio(null,0L);
        assertEquals(0F,calculatedRatio,0);
    }

    @Test
    public void test_calculateRatio_secundary_null() {
        Float calculatedRatio = GeneralUtils.calculateRatio(4L,null);
        assertEquals(4F,calculatedRatio,0);
    }


}