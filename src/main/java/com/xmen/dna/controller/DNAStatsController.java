package com.xmen.dna.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmen.dna.dto.response.VerificationStatsDTO;
import com.xmen.dna.service.DNAStatsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Class to resolve request for endpoint /stats
 * @author fr.rodriguez
 */
@RestController
@Validated
public class DNAStatsController {

    private DNAStatsService dnaStatsService;

    public DNAStatsController(DNAStatsService dnaStatsService) {
        this.dnaStatsService = dnaStatsService;
    }

    @ApiOperation(value = "This endpoint confirms that a given DNA sequence holds mutant DNA")
    @ApiResponses( value = {
            @ApiResponse(code = 200 , message = "success" , response=VerificationStatsDTO.class)
    })
    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VerificationStatsDTO> getMutantStatistics(@RequestParam(required = false) String algorithmName) {
        VerificationStatsDTO mutantsStats = dnaStatsService.getMutantsStatistics(algorithmName);
        return ResponseEntity.ok().body(mutantsStats);
    }

}
