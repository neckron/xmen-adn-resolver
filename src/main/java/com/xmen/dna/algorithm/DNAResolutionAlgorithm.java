package com.xmen.dna.algorithm;

import com.xmen.dna.exception.SquareMatrixException;

/**
 * Class to determine the isMutant behavior to its subclases
 * @author fr.rodriguez
 */
public interface DNAResolutionAlgorithm {

    /**
     * This operation validates if the input dna corresponds to mutant dna
     * @param dna , seuqnce to validate by the algorithm.
     * @return true if dna corresponds to mutant dna. false otherwise
     */
    boolean isMutant(String[] dna) throws SquareMatrixException;

}
