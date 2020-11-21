package com.xmen.dna.algorithm;

public interface DNAResolutionAlgorithm {

    /**
     * This operation validates if the input dna corresponds to mutant dna
     * @param dna
     * @return true if dna corresponds to mutant dna. false otherwise
     */
    boolean isMutant(String[] dna);

}
