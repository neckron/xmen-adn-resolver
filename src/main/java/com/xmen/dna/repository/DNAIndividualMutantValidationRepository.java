package com.xmen.dna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xmen.dna.domain.DNAIndividualMutantValidation;
import com.xmen.dna.dto.response.StatsDTO;

@Repository
public interface DNAIndividualMutantValidationRepository extends CrudRepository<DNAIndividualMutantValidation, Long> {

    /**
     *
     * @param dnaSequence
     * @param algorithm
     * @return
     */
    DNAIndividualMutantValidation findByDnaSequenceAndAlgorithm(String dnaSequence, String algorithm);

    /**
     *
     * @param algorithmName
     * @return
     */
    @Query("SELECT " +
            "    new com.xmen.dna.dto.response.StatsDTO(dna.algorithm, COUNT(dna), dna.isMutant) " +
            "FROM " +
            "    DNAIndividualMutantValidation dna " +
            "WHERE "+
            " dna.algorithm = :algorithmName "+
            "GROUP BY " +
            "    dna.algorithm, isMutant")

    List<StatsDTO> getMutantsStats(String algorithmName);

}
