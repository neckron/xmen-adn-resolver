package com.xmen.dna.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VerificationStatsDTO {

    private Long count_mutant_dna = 0L;
    private Long count_human_dna = 0L;
    private Float ratio;
}
