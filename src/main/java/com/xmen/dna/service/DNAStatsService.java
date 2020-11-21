package com.xmen.dna.service;

import java.util.List;

import com.xmen.dna.dto.response.StatsDTO;

public interface DNAStatsService {

    List<StatsDTO> getMutantsStatistics(String algorithm);

}
