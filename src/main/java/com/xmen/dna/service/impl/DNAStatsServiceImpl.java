package com.xmen.dna.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xmen.dna.dto.response.StatsDTO;
import com.xmen.dna.repository.DNAIndividualMutantValidationRepository;
import com.xmen.dna.service.DNAStatsService;

/**
 *
 */
@Service
public class DNAStatsServiceImpl implements DNAStatsService {

    private DNAIndividualMutantValidationRepository dnaIndividualMutantValidationRepository;

    public DNAStatsServiceImpl(DNAIndividualMutantValidationRepository dnaIndividualMutantValidationRepository) {
        this.dnaIndividualMutantValidationRepository = dnaIndividualMutantValidationRepository;
    }

    @Override
    public List<StatsDTO> getMutantsStatistics(String algorithm) {
        return Collections.emptyList();
    }
}
