package com.xmen.dna.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Holds response to a mutant validation
 * @author fr.rodriguez
 */
@Getter
@Setter
@Builder
public class DNAResponseDTO {

    @ApiModelProperty(example = "validation finished successfully")
    private String message;
    @ApiModelProperty(example = "true|false")
    private Boolean isMutant;

    public DNAResponseDTO(String message, Boolean isMutant) {
        this.message = message;
        this.isMutant = isMutant;
    }
}
