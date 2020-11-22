package com.xmen.dna.util;

import static org.junit.jupiter.api.Assertions.*;

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
}