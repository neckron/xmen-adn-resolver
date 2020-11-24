package com.xmen.dna.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.xmen.dna.exception.SquareMatrixException;

@RunWith(JUnit4.class)
public class GeneralUtilsTest {

    private Pattern DNApattern = Pattern.compile("^[ACTG]*$");
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

    @Test
    public void test_initSquareMatrix_ok() throws SquareMatrixException {
        String[] stringArray = { "TAACC", "CCCCC", "GGGGG", "TTTTT", "AAAAA" };
        char[][] squareMatrix = GeneralUtils.initSquareMatrix(stringArray,DNApattern);
        assertEquals("TAACC",String.valueOf(squareMatrix[0]));
        assertEquals("CCCCC",String.valueOf(squareMatrix[1]));
        assertEquals("GGGGG",String.valueOf(squareMatrix[2]));
        assertEquals("TTTTT",String.valueOf(squareMatrix[3]));
        assertEquals("AAAAA",String.valueOf(squareMatrix[4]));
    }

    @Test(expected = SquareMatrixException.class)
    public void test_initSquareMatrix_is_not_NxN_missed_row() throws SquareMatrixException {
                String[] stringArray = { "AAAAA", "CCCCC", "GGGGG", "TTTTT"};
        char[][] squareMatrix = GeneralUtils.initSquareMatrix(stringArray,DNApattern);
    }

    @Test(expected = SquareMatrixException.class)
    public void test_initSquareMatrix_is_not_NxN_missed_column() throws SquareMatrixException {
        String[] stringArray = { "AAAAA", "CCCCC", "GGGGG", "TTTTT", "TTTT"};
        char[][] squareMatrix = GeneralUtils.initSquareMatrix(stringArray,DNApattern);
    }

    @Test(expected = SquareMatrixException.class)
    public void test_initSquareMatrix_is_not_NxN_not_DNA_pattern() throws SquareMatrixException {
        String[] stringArray = { "AAAAA", "CCCCC", "GGGGG", "TTTTT", "TTxTT"};
        char[][] squareMatrix = GeneralUtils.initSquareMatrix(stringArray,DNApattern);
    }

    @Test(expected = SquareMatrixException.class)
    public void test_initSquareMatrix_pattern_null() throws SquareMatrixException {
        String[] stringArray = { "AAAAA", "CCCCC", "GGGGG", "TTTTT", "TTxTT"};
        char[][] squareMatrix = GeneralUtils.initSquareMatrix(stringArray,null);
    }

    @Test(expected = SquareMatrixException.class)
    public void test_initSquareMatrix_dna_null() throws SquareMatrixException {
        String[] stringArray = { "AAAAA", "CCCCC", "GGGGG", "TTTTT", "TTxTT"};
        char[][] squareMatrix = GeneralUtils.initSquareMatrix(null,DNApattern);
    }


}