package com.xmen.dna.algorithm.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.format.annotation.NumberFormat;

import com.xmen.dna.algorithm.DNAResolutionAlgorithm;
import com.xmen.dna.service.DNAStatsService;

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
        result = DNAAlgorithmFactory.validateAlgorithm("default");
        assertEquals("default",result);
        result = DNAAlgorithmFactory.validateAlgorithm("simple");
        assertEquals("simple",result);
        result = DNAAlgorithmFactory.validateAlgorithm(null);
        assertEquals("default",result);
        result = DNAAlgorithmFactory.validateAlgorithm("");
        assertEquals("default",result);
        result = DNAAlgorithmFactory.validateAlgorithm("anyother");
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