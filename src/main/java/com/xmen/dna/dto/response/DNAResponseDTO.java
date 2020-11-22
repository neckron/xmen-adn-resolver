package com.xmen.dna.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DNAResponseDTO {

    private String message;
    private Boolean isMutant;

    public DNAResponseDTO(String message, Boolean isMutant) {
        this.message = message;
        this.isMutant = isMutant;
    }
}
