package com.xmen.dna.service.impl;

import static com.xmen.dna.util.GeneralUtils.getStringFromStringArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xmen.dna.algorithm.impl.DNAAlgorithmFactory;
import com.xmen.dna.domain.DNAIndividualMutantValidation;
import com.xmen.dna.dto.request.DNARequestDTO;
import com.xmen.dna.dto.response.DNAResponseDTO;
import com.xmen.dna.exception.SquareMatrixException;
import com.xmen.dna.repository.DNAIndividualMutantValidationRepository;
import com.xmen.dna.service.DNAResolverService;

/**
 * Class used to comunicate the Rest controller, repository and algorithm components.
 * @author fr.rodriguez
 */
@Service
public class DNAMutantResolverServiceImpl implements DNAResolverService {

    private static final Logger logger = LoggerFactory.getLogger(DNAMutantResolverServiceImpl.class);
    private DNAIndividualMutantValidationRepository mutantValidationRepository;

    public DNAMutantResolverServiceImpl(DNAIndividualMutantValidationRepository mutantValidationRepository) {
        this.mutantValidationRepository = mutantValidationRepository;
    }

    /**
     * This service methods looks for any previous mutant validation in dbase, if something found, returns it.
     * Otherwise it will validate it using validation algorithm and stores the result in dbase.
     * @param dna, string array that holds the DNA to be validated, algorithm used to validate if individual is mutant or not.
     * @return DNAResponseDTO,
     */
    @Override
    public DNAResponseDTO isMutant(DNARequestDTO dna) {
        Boolean validationResult=false;
        String plainDNA = getStringFromStringArray(dna.getDna());
        String algorithmName = DNAAlgorithmFactory.validateAlgorithmName(dna.getValidationAlgorithm());
        DNAIndividualMutantValidation individualMutantValidation = mutantValidationRepository.findByDnaSequenceAndAlgorithm(plainDNA, algorithmName);
        if(individualMutantValidation != null){
            logger.info("validation founded for DNA using algorithm {}" , dna.getValidationAlgorithm());
            validationResult = individualMutantValidation.getIsMutant();
        }else {
            logger.info("no validation has founded for DNA using algorithm {} running algorithm..." , algorithmName);
            DNAAlgorithmFactory algorithmFactory = new DNAAlgorithmFactory();
            try {
                validationResult = algorithmFactory.supplyAlgorithm(algorithmName).isMutant(dna.getDna());
                logger.info("saving validation result in dBase");
                mutantValidationRepository.save(DNAIndividualMutantValidation.builder()
                        .isMutant(validationResult)
                        .dnaSequence(plainDNA)
                        .algorithm(algorithmName).build());

            }
            catch (SquareMatrixException e) {
                logger.error("Can't save validation due {}",e.getMessage());
            }

        }
        return new DNAResponseDTO("validation finished successfully", validationResult);
    }

}
