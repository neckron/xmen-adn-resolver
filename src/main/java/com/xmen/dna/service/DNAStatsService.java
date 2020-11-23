package com.xmen.dna.service;

import com.xmen.dna.dto.response.VerificationStatsDTO;

public interface DNAStatsService {

    VerificationStatsDTO getMutantsStatistics(String algorithm);

}
