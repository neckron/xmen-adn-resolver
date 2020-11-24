package com.xmen.dna.algorithm.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.xmen.dna.algorithm.DNAResolutionAlgorithm;

@RunWith(JUnit4.class)
public class DNAAlgorithmFactoryTest {

    private DNAAlgorithmFactory dnaAlgorithmFactory;

    @Before
    public void init() {
        this.dnaAlgorithmFactory = new DNAAlgorithmFactory();
    }

    @Test
    public void test_validate_algorithm() {
        String result;
        result = DNAAlgorithmFactory.validateAlgorithmName("default");
        assertEquals("default",result);
        result = DNAAlgorithmFactory.validateAlgorithmName("simple");
        assertEquals("simple",result);
        result = DNAAlgorithmFactory.validateAlgorithmName(null);
        assertEquals("default",result);
        result = DNAAlgorithmFactory.validateAlgorithmName("");
        assertEquals("default",result);
        result = DNAAlgorithmFactory.validateAlgorithmName("anyother");
        assertEquals("default",result);
    }

    @Test
    public void text_supply_algorithm_null_algorithmname() {
        DNAResolutionAlgorithm algorithm = this.dnaAlgorithmFactory.supplyAlgorithm(null);
        assertEquals(DNADefaultResolution.class, algorithm.getClass());
    }

    @Test
    public void text_supply_algorithm_empty_algoirthmname() {
        DNAResolutionAlgorithm algorithm = this.dnaAlgorithmFactory.supplyAlgorithm("");
        assertEquals(DNADefaultResolution.class, algorithm.getClass());
    }

    @Test
    public void text_supply_algorithm_simple_algorithmname() {
        DNAResolutionAlgorithm algorithm = this.dnaAlgorithmFactory.supplyAlgorithm("simple");
        assertEquals(DNASimpleResolution.class, algorithm.getClass());
    }

    @Test
    public void text_supply_algorithm_default_algorithmname() {
        DNAResolutionAlgorithm algorithm = this.dnaAlgorithmFactory.supplyAlgorithm("default");
        assertEquals(DNADefaultResolution.class, algorithm.getClass());
    }


}