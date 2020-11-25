package com.xmen.dna.algorithm.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.xmen.dna.exception.SquareMatrixException;
import com.xmen.dna.util.GeneralUtils;


@RunWith(JUnit4.class)
public class DNADefaultResolutionTest {

    @Test
    public void test_validateRowsAndColumns_matches_found_at_rows() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        char[][] matrix = GeneralUtils.initSquareMatrix(
                new String[] {
                        "TGGGGA",
                        "AAGTGC",
                        "ATTTGT",
                        "AAAACT",
                        "TCCCTA",
                        "TCCCCC" },
                Pattern.compile("^[ACTG]*$"));

        Long hits =  dnaDefaultResolution.validateRowsAndColumns(matrix,2);
        assertEquals(2,hits);
    }

    @Test
    public void test_validateRowsAndColumns_matches_found_at_columns() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        char[][] matrix = GeneralUtils.initSquareMatrix(
                new String[] {
                        "AGGTGA",
                        "AAGTGC",
                        "ATTTGT",
                        "ACAACT",
                        "TCCCTA",
                        "TCCACC" },
                Pattern.compile("^[ACTG]*$"));

        Long hits =  dnaDefaultResolution.validateRowsAndColumns(matrix,2);
        assertEquals(1,hits);
    }

    @Test
    public void test_validateRowsAndColumns_not_matches_found() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        char[][] matrix = GeneralUtils.initSquareMatrix(
                new String[] {
                        "AGTCGA",
                        "TCAGCT",
                        "AGTCGA",
                        "TCAGCT",
                        "AGTCGA",
                        "TCAGCT" },
                Pattern.compile("^[ACTG]*$"));

        Long hits =  dnaDefaultResolution.validateRowsAndColumns(matrix,2);
        assertEquals(0,hits);
    }

    @Test
    public void test_validateRowsAndColumns_matches_found_at_rows_min_matches_changed() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        char[][] matrix = GeneralUtils.initSquareMatrix(
                new String[] {
                        "TGGGGA",
                        "AAGTGC",
                        "ATTTGT",
                        "AAAACT",
                        "TCCCTA",
                        "TCCCCC" },
                Pattern.compile("^[ACTG]*$"));

        Long hits =  dnaDefaultResolution.validateRowsAndColumns(matrix,3);
        assertEquals(3,hits);
    }

    @Test
    public void test_isMutant_true() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        // 3 matches
        String[] dnaSequence = new String[] {
                "TGGGGA",
                "AAGTGC",
                "ATTTGT",
                "AAAACT",
                "TCCCTA",
                "TCCCCC" };

        boolean mutantValidationResult =  dnaDefaultResolution.isMutant(dnaSequence);
        assertTrue(mutantValidationResult);
    }

    @Test
    public void test_isMutant_false() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        String[] dnaSequence = new String[] {
                "TGAGGA",
                "AAGTGC",
                "ATTTGT",
                "ATAACT",
                "TCGCTA",
                "TCCACC" };
        assertFalse(dnaDefaultResolution.isMutant(dnaSequence));
    }

    @Test(expected = SquareMatrixException.class)
    public void test_isMutant_matrix_not_could_be_created() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        String[] dnaSequence = new String[] {
                "TGAGG", // miss last column
                "AAGTGC",
                "ATTTGT",
                "ATAACT",
                "TCGCTA",
                "TCCACC" };
        assertFalse(dnaDefaultResolution.isMutant(dnaSequence));
    }

    @Test
    public void validateDiagonalLeft_1hit() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        char[][] matrix = GeneralUtils.initSquareMatrix(
                new String[] {
                        "CGCGCG",
                        "TATATA",
                        "CGCGCA",
                        "TATAAT",
                        "GCGAAC",
                        "TAATAA"
                },
                Pattern.compile("^[ACTG]*$"));

        Long hits =  dnaDefaultResolution.validateDiagonalLeft(matrix,2, 4);
        assertEquals(1,hits);
    }

    @Test
    public void validateDiagonalright_1hit() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        char[][] matrix = GeneralUtils.initSquareMatrix(
                new String[] {
                        "ACTCTC",
                        "CAGTCT",
                        "CTAGCT",
                        "GTCAGT",
                        "GTCTCG",
                        "GTGTGT"
                },
                Pattern.compile("^[ACTG]*$"));

        Long hits =  dnaDefaultResolution.validateDiagonalRight(matrix,2,4);
        assertEquals(2,hits);
    }

    @Test
    public void validateDiagonaRight_more_than2hits() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        char[][] matrix = GeneralUtils.initSquareMatrix(
                new String[] {
                        "ACTCTC",
                        "CAGTCT",
                        "CCAGCT",
                        "GTCAGT",
                        "GTCCCG",
                        "GTGTGT"
                },
                Pattern.compile("^[ACTG]*$"));

        Long hits =  dnaDefaultResolution.validateDiagonalRight(matrix,2,4);
        assertEquals(2,hits);
    }

    @Test
    public void test_isMutant_exercise_case() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        // 3 matches
        String[] dnaSequence = new String[] {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};;

        boolean mutantValidationResult =  dnaDefaultResolution.isMutant(dnaSequence);
        assertTrue(mutantValidationResult);
    }

    @Test
    public void test_isMutant_3x3_matrix() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        // 3 matches
        String[] dnaSequence = new String[] { "ATG", "CAG", "TTA" };

        boolean mutantValidationResult =  dnaDefaultResolution.isMutant(dnaSequence);
        assertFalse(mutantValidationResult);
    }

    @Test
    public void test_isMutant_4x4_matrix() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        // 3 matches
        String[] dnaSequence = new String[] {
                "ATGC",
                "CACC",
                "GCAT",
                "CGTA"};

        boolean mutantValidationResult =  dnaDefaultResolution.isMutant(dnaSequence);
        assertTrue(mutantValidationResult);
    }

    @Test
    public void test_isMutant_4x4_matrix_row_and_column() throws SquareMatrixException {
        DNADefaultResolution dnaDefaultResolution = new DNADefaultResolution();
        // 3 matches
        String[] dnaSequence = new String[] {
                "GGGG",
                "GACC",
                "GCAT",
                "GGTA"};

        boolean mutantValidationResult =  dnaDefaultResolution.isMutant(dnaSequence);
        assertTrue(mutantValidationResult);
    }

}