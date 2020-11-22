package com.xmen.dna.dto.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "DNARequestDTO", description = "Holds information about DNA to be validated")
@Getter
@Setter
@Builder(toBuilder = true)
public class DNARequestDTO {

    @NotEmpty(message="You should specify a valid dna sequence")
    @ApiModelProperty(required = true)
    private String[] dna;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @ApiModelProperty(name = "dna validation algorithm", allowableValues = "default,simple")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String validationAlgorithm;

}
