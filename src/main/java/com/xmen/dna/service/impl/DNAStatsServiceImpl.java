package com.xmen.dna.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xmen.dna.algorithm.impl.DNAAlgorithmFactory;
import com.xmen.dna.dto.response.StatsDTO;
import com.xmen.dna.dto.response.VerificationStatsDTO;
import com.xmen.dna.repository.DNAIndividualMutantValidationRepository;
import com.xmen.dna.service.DNAStatsService;
import com.xmen.dna.util.GeneralUtils;

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
    public VerificationStatsDTO getMutantsStatistics(String algorithm) {
        List<StatsDTO> stats = dnaIndividualMutantValidationRepository.getMutantsStats(DNAAlgorithmFactory.ValidateAlgorithmName(algorithm));
        return transformStats(stats);
    }

    private VerificationStatsDTO transformStats(List<StatsDTO> statsDTOList) {
        VerificationStatsDTO verificationStatsDTO = new VerificationStatsDTO();
        for(StatsDTO stat: statsDTOList){
            if(stat.getType()) {
                verificationStatsDTO.setCount_mutant_dna(stat.getCount());
            }else {
                verificationStatsDTO.setCount_human_dna(stat.getCount());
            }
        }

        verificationStatsDTO.setRatio(GeneralUtils.calculateRatio(verificationStatsDTO.getCount_mutant_dna(), verificationStatsDTO.getCount_human_dna()));
        return verificationStatsDTO;
    }

}
