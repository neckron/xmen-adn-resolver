package com.xmen.dna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xmen.dna.domain.DNAIndividualMutantValidation;

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
     *
    @Query("SELECT " +
            "    new com.path.to.SurveyAnswerStatistics(v.answer, COUNT(v)) " +
            "FROM " +
            "    Survey v " +
            "GROUP BY " +
            "    v.answer")
    StatsDTO getMutantsStats(String algorithmName);*/

}
