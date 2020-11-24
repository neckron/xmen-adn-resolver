package com.xmen.dna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * entity that represents dna_mutant_table in the dBase
 * @author fr.rodriguez
 */
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dna_mutant_validation")
public class DNAIndividualMutantValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "validation_algorithm")
    private String algorithm;

    @Column(name = "dna_sequence")
    private String dnaSequence;

    @Column(name = "individual_name")
    private String individualName;

    @Column(name = "is_mutant")
    private Boolean isMutant;

}
