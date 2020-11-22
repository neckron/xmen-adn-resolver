package com.xmen.dna.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xmen.dna.dto.request.DNARequestDTO;
import com.xmen.dna.dto.response.DNAResponseDTO;
import com.xmen.dna.service.DNAResolverService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Validates if an individual is mutant or human depending its dna sequence")
@RestController
@Validated
public class DNAMutantValidationController {

    private DNAResolverService dnaResolverService;

    public DNAMutantValidationController(DNAResolverService dnaResolverService) {
        this.dnaResolverService = dnaResolverService;
    }

    @ApiOperation(value = "This endpoint confirms that a given DNA sequence holds mutant DNA")
    @ApiResponses(  value = {
            @ApiResponse(code = 200 , message = "the validated dna corresponds to a mutant individual" , response=DNAResponseDTO.class),
            @ApiResponse(code = 403 , message = "the validated dna corresponds to a human individual" , response=DNAResponseDTO.class),
            @ApiResponse(code = 404 , message = "the validated dna corresponds to a human individual" , response=DNAResponseDTO.class),
            @ApiResponse(code = 500 , message = "internal server error" , response=DNAResponseDTO.class)
    })
    @PostMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<DNAResponseDTO> isMutant(
            @ApiParam("individual dna sequence to be validated by algorithm")
            @Valid @RequestBody DNARequestDTO requestBody) {
        DNAResponseDTO responseBody = dnaResolverService.isMutant(requestBody);
        if(responseBody.getIsMutant()) {
            return ResponseEntity.ok().body(responseBody);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
        }
    }

}
