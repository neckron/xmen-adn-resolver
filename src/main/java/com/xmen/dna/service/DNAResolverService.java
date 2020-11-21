package com.xmen.dna.service;

import com.xmen.dna.dto.request.DNARequestDTO;
import com.xmen.dna.dto.response.DNAResponseDTO;

public interface DNAResolverService {

    DNAResponseDTO isMutant(DNARequestDTO dnaRequestDTO);

}
