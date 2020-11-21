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

@Api(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Contacts.", tags = {"contact"})
@RestController
@Validated
public class DNAMutantValidationController {

    private DNAResolverService dnaResolverService;

    public DNAMutantValidationController(DNAResolverService dnaResolverService) {
        this.dnaResolverService = dnaResolverService;
    }

    @ApiOperation(value = "This endpoint confirms that a given DNA sequence holds mutant DNA")
    @ApiResponses( value = {
            @ApiResponse(code = 200 , message = "success" , response=String.class),
            @ApiResponse(code = 403 , message = "failure" , response=String.class)
    })
    @PostMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<DNAResponseDTO> isMutant(
            @ApiParam("dna sequence to validate can be null nor empty")
            @Valid @RequestBody DNARequestDTO requestBody) {
        DNAResponseDTO responseBody = dnaResolverService.isMutant(requestBody);
        if(responseBody.getMutant()) {
            return ResponseEntity.ok().body(responseBody);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
        }
    }

}
