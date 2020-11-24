package com.xmen.dna.algorithm.impl;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmen.dna.algorithm.DNAResolutionAlgorithm;
import com.xmen.dna.exception.SquareMatrixException;
import com.xmen.dna.util.GeneralUtils;

/**
 * This class resolve a DNA sequence to determine that an individual is a mutant or human.
 * @author fr.rodriguez
 */
public class DNADefaultResolution implements DNAResolutionAlgorithm {

    private static final Logger logger = LoggerFactory.getLogger(DNADefaultResolution.class);
    /**
     * minimal amount of matches that make a DNA sequence mutant.
     */
    private int minMatches = 2;
    /**
     * pattern to validate DNA is compound only for ACTG letters.
     */
    private static final Pattern dnaPattern = Pattern.compile("^[ACTG]*$");

    @Override
    public boolean isMutant(String[] dna) throws SquareMatrixException {
        boolean result=false;
        long matches = 0L;
        char[][] dnaMatrix = GeneralUtils.initSquareMatrix(dna, dnaPattern);
        // find pattern matches in dnaMatrix rows
        matches += validateRowsAndColumns(dnaMatrix,minMatches);
        // find diagonals
        if(matches>=minMatches) {
            result = true;
        }
        return result;
    }

    /**
     * retrieves the number of coincidences in a dna sequence chunk
     * @param sequence
     * @return
     */
    public int getSequenceChunkHits(String sequence) {
        int matchesCount = 0;
        matchesCount += StringUtils.countMatches(sequence,"AAAA");
        matchesCount += StringUtils.countMatches(sequence,"TTTT");
        matchesCount += StringUtils.countMatches(sequence,"CCCC");
        matchesCount += StringUtils.countMatches(sequence, "GGGG");
        return matchesCount;

    }

    /**
     * Validate if the dna matrix rows and columns have sequence chunk coincidences
     * it will short circuiting if finds >= minMatches hits.
     * @param dnaMatrix, seuqnce to be analized
     * @param minMatches, minimun number of coincidences founded
     * @return
     */
    public long validateRowsAndColumns(char[][] dnaMatrix, int minMatches) {
        int n = dnaMatrix.length;
        long totalHits = 0;
        for (int i=0 ; i<n && totalHits<minMatches; i++) {
            int rowHits = getSequenceChunkHits(String.valueOf(dnaMatrix[i]));
            if(rowHits>0) {
                logger.info("algorithm {} has founded: {} in row {} ",this.getClass(), rowHits,i);
            }
            totalHits += rowHits;
        }

        if(totalHits<minMatches) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n && totalHits<minMatches ; j++) {
                for (int i = 0; i < n && totalHits<minMatches ; i++) {
                    stringBuilder.append(dnaMatrix[i][j]);
                }
                int columnHits = getSequenceChunkHits(stringBuilder.toString());
                if (columnHits > 0) {
                    logger.info("algorithm {} has founded: {} in column {} ",this.getClass(), columnHits,j);
                }
                stringBuilder = new StringBuilder();
                totalHits += columnHits;
            }
        }

        return totalHits;
    }

}
