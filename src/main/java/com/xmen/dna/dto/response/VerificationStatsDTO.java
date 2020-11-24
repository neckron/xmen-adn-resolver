package com.xmen.dna.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Hold the transformed information from StatsDTO in order to be returned to as response on /stats endpoint
 * @author fr.rodriguez
 */
@Getter
@Setter
@NoArgsConstructor
public class VerificationStatsDTO {

    @ApiModelProperty(example = "40")
    private Long count_mutant_dna = 0L;
    @ApiModelProperty(example = "100")
    private Long count_human_dna = 0L;
    @ApiModelProperty(example = "0.4")
    private Float ratio;
}
