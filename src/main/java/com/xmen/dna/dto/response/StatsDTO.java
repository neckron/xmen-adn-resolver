package com.xmen.dna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatsDTO {

    private String algorithmName;
    private Long count;
    private Boolean type;

}
