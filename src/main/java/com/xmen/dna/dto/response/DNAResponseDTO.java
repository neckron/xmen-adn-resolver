package com.xmen.dna.dto.response;

public class DNAResponseDTO {

    private String message;
    private Boolean isMutant;

    public DNAResponseDTO(String message, Boolean isMutant) {
        this.message = message;
        this.isMutant = isMutant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getMutant() {
        return isMutant;
    }

    public void setMutant(Boolean mutant) {
        isMutant = mutant;
    }
}
