package com.xmen.dna.algorithm.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.xmen.dna.algorithm.DNAResolutionAlgorithm;

/**
 * This class will be resolving and creating a DNAResolutionAlgorithm given an algorithm name
 * @author fr.rodriguez
 */
public class DNAAlgorithmFactory {

    private static final Map<String, Supplier<DNAResolutionAlgorithm>> ALGORITHM_SUPPLIER;
    public static final String DEFAULT = "default";
    public static final String SIMPLE = "simple";

    /**
     * Filling the available algorithm map
     */
    static {
        final Map<String, Supplier<DNAResolutionAlgorithm>> algorithms = new HashMap<>();
        algorithms.put(DEFAULT, DNADefaultResolution::new);
        algorithms.put(SIMPLE, DNASimpleResolution::new);
        ALGORITHM_SUPPLIER = Collections.unmodifiableMap(algorithms);
    }

    /**
     * Retrieving a DNAResolution algorithm given an algorithm name
     * @param algorithmName, when it comes null or it's not registered in algorithm supplier, method will returns an instance of DNADefaultResolution
     * @return DNAResolutionAlgorithm instance.
     */
    public DNAResolutionAlgorithm supplyAlgorithm(String algorithmName) {
        Supplier<DNAResolutionAlgorithm> algorithm = ALGORITHM_SUPPLIER.get(algorithmName);
        if (algorithm == null) {
            algorithm = ALGORITHM_SUPPLIER.get(DEFAULT);
        }
        return algorithm.get();
    }

    /**
     * This method validates an algorithmName
     * @param algorithmName, method returns DEFAULT if algorithmName is not registered in upplier map
     * @return
     */
    public static String validateAlgorithmName(String algorithmName) {
        if (ALGORITHM_SUPPLIER.containsKey(algorithmName)) {
            return algorithmName;
        }
        else {
            return DEFAULT;
        }
    }

}
