package com.xmen.dna.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.xmen.dna.algorithm.DNAResolutionAlgorithm;
import com.xmen.dna.algorithm.impl.DNAAlgorithmFactory;
import com.xmen.dna.domain.DNAIndividualMutantValidation;
import com.xmen.dna.dto.request.DNARequestDTO;
import com.xmen.dna.dto.response.DNAResponseDTO;
import com.xmen.dna.exception.SquareMatrixException;
import com.xmen.dna.repository.DNAIndividualMutantValidationRepository;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(DNAAlgorithmFactory.class)
@RunWith(SpringRunner.class)
public class DNAMutantResolverServiceImplTest {

    @InjectMocks
    private DNAMutantResolverServiceImpl dnaMutantResolverService;

    @Mock
    private DNAIndividualMutantValidationRepository mutantValidationRepository;

    @Mock
    private DNAAlgorithmFactory dnaAlgorithmFactory;

    @Mock
    private DNAResolutionAlgorithm dnaResolutionAlgorithm;


    @Test
    public void test_isMutant_validation_already_in_dbase() {
        when(mutantValidationRepository.findByDnaSequenceAndAlgorithm("AAAACCCC", "simple"))
                .thenReturn(DNAIndividualMutantValidation.builder().isMutant(true).build());

        DNAResponseDTO response = dnaMutantResolverService.isMutant(DNARequestDTO.builder().dna(new String[]{"AAAA","CCCC"}).validationAlgorithm("simple").build());
        assertTrue(response.getIsMutant());
        assertEquals("validation finished successfully", response.getMessage());
    }

    @Test
    public void test_isMutant_validation_not_in_dbase() throws SquareMatrixException {
        when(mutantValidationRepository.findByDnaSequenceAndAlgorithm("AAAACCCC", "simple"))
                .thenReturn(null);

        when(dnaAlgorithmFactory.supplyAlgorithm(any(String.class))).thenReturn(dnaResolutionAlgorithm);
        when(dnaResolutionAlgorithm.isMutant(any(String[].class))).thenReturn(false);

        DNAResponseDTO response = dnaMutantResolverService.isMutant(DNARequestDTO.builder().dna(new String[]{"AAAA","CCCC"}).validationAlgorithm("simple").build());
        assertFalse(response.getIsMutant());
        assertEquals("validation finished successfully", response.getMessage());
    }

}