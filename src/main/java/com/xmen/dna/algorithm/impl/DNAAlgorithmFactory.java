package com.xmen.dna.algorithm.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.xmen.dna.algorithm.DNAResolutionAlgorithm;



public class DNAAlgorithmFactory {

    private static final Map<String, Supplier<DNAResolutionAlgorithm>> ALGORITHM_SUPPLIER;
    public static final String DEFAULT = "default";
    public static final String SIMPLE = "simple";


    static {
        final Map<String, Supplier<DNAResolutionAlgorithm>> algorithms = new HashMap<>();
        algorithms.put(DEFAULT, DNADefaultResolution::new);
        algorithms.put(SIMPLE, DNASimpleResolution::new);
        ALGORITHM_SUPPLIER = Collections.unmodifiableMap(algorithms);
    }


    public static DNAResolutionAlgorithm supplyAlgorithm(String algorithmName) {
        Supplier<DNAResolutionAlgorithm> algorithm = ALGORITHM_SUPPLIER.get(algorithmName);
        if (algorithm == null) {
            algorithm = ALGORITHM_SUPPLIER.get(DEFAULT);
        }
        return algorithm.get();
    }

    public static String validateAlgorithm(String algorithmName) {
        if (ALGORITHM_SUPPLIER.containsKey(algorithmName)) {
            return algorithmName;
        }
        else {
            return DEFAULT;
        }
    }

}
