package com.xmen.dna.dto.request;

import javax.validation.constraints.NotEmpty;

public class DNARequestDTO {

    @NotEmpty(message="You should specify an dna value")
    private String[] dna;

    private String name;
    private String validationAlgorithm;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public String getValidationAlgorithm() {
        return validationAlgorithm;
    }

    public void setValidationAlgorithm(String validationAlgorithm) {
        this.validationAlgorithm = validationAlgorithm;
    }
}
