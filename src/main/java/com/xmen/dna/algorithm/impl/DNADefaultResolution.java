package com.xmen.dna.algorithm.impl;

import static com.xmen.dna.util.GeneralUtils.generate;

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
    private static int minMatches = 2;
    /**
     *
     */
    private static int matchStringLenght = 4;
    /**
     * pattern to validate DNA is compound only for ACTG letters.
     */
    private static final Pattern dnaPattern = Pattern.compile("^[ACTG]*$");

    private static final String AAAA = generate('A',matchStringLenght);
    private static final String GGGG = generate('G',matchStringLenght);
    private static final String TTTT = generate('T',matchStringLenght);
    private static final String CCCC = generate('C',matchStringLenght);

    @Override
    public boolean isMutant(String[] dna) throws SquareMatrixException {
        boolean result=false;
        long matches = 0L;
        char[][] dnaMatrix = GeneralUtils.initSquareMatrix(dna, dnaPattern);

        if(dnaMatrix.length>=matchStringLenght) {
            // find pattern matches in dnaMatrix rows
            matches += validateRowsAndColumns(dnaMatrix,minMatches);

            // find pattern matches in dnaMatrix first diagonal
            if(matches<2) {
                matches += validateDiagonalLeft(dnaMatrix,minMatches,matchStringLenght);
            }

            // find pattern matches in dnaMatrix second diagonal
            if(matches<2) {
                matches += validateDiagonalRight(dnaMatrix,minMatches,matchStringLenght);
            }

            if(matches>=minMatches) {
                result = true;
            }
        }


        return result;
    }

    /**
     * retrieves the number of coincidences in a dna sequence chunk
     * @param sequence
     * @return
     */
    public long getSequenceChunkHits(String sequence, int minMatches) {
        int matchesCount = 0;
        matchesCount += StringUtils.countMatches(sequence,AAAA);

        if (matchesCount<minMatches) {
            matchesCount += StringUtils.countMatches(sequence,TTTT);
        }

        if (matchesCount<minMatches) {
            matchesCount += StringUtils.countMatches(sequence,CCCC);
        }

        if (matchesCount<minMatches) {
            matchesCount += StringUtils.countMatches(sequence, GGGG);
        }

        return matchesCount;
    }

    private long match(String sequenceToMatch, int minMatches){
        long rowHits = getSequenceChunkHits(sequenceToMatch,minMatches);
        if(rowHits>0) {
            logger.info("algorithm {} has founded: {} in row {} ",this.getClass(), rowHits);
        }
        return rowHits;
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
            totalHits += match(String.valueOf(dnaMatrix[i]),minMatches);
          }

        if(totalHits<minMatches) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n && totalHits<minMatches ; j++) {
                for (int i = 0; i < n && totalHits<minMatches ; i++) {
                    stringBuilder.append(dnaMatrix[i][j]);
                }
                totalHits += match(stringBuilder.toString(),minMatches);
                stringBuilder = new StringBuilder();

            }
        }

        return totalHits;
    }

    public long validateDiagonalLeft(char[][] dnaMatrix, int minMatches, int k) {
        int n = dnaMatrix.length;
        long totalHits = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=k-1,j=k-1; i<n && totalHits<minMatches ;i++,j++) {
            for(int x=0,y=j; x<=i ;x++,y--) {
                stringBuilder.append(dnaMatrix[x][y]);
            }
            totalHits += match(stringBuilder.toString(),minMatches);
            stringBuilder = new StringBuilder();
        }

        // cambiar 4
        for(int i=n-(k-1),j=n-(k-1); i>=0 && totalHits<minMatches ;i--,j--) {
            for(int x=n-1,y=j; x>=i ; x--,y++) {
                stringBuilder.append(dnaMatrix[x][y]);
            }
            totalHits += match(stringBuilder.toString(),minMatches);
            stringBuilder = new StringBuilder();
        }

        return totalHits;
    }

    public long validateDiagonalRight(char[][] dnaMatrix, int minMatches, int k) {
        int n = dnaMatrix.length;
        long totalHits = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i= n-(k-1),j=n-k; i>=0 && totalHits<minMatches ;i--,j++) {
            for(int x=n-1,y=j; x>=i && y>=0 ;x--,y--) {
                stringBuilder.append(dnaMatrix[x][y]);
            }
            totalHits += match(stringBuilder.toString(),minMatches);
            stringBuilder = new StringBuilder();
        }

        for(int i=n-k,j=n-(k-1); i<n-1 && totalHits<minMatches ;i++,j--) {
            for(int x=0,y=j; x<=i && y>=0 ;x++,y++) {
                stringBuilder.append(dnaMatrix[x][y]);
            }
            totalHits += match(stringBuilder.toString(),minMatches);
            stringBuilder = new StringBuilder();
        }

        return totalHits;
    }

}
