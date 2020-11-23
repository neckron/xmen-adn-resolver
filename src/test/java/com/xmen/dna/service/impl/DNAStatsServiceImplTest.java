package com.xmen.dna.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.xmen.dna.dto.response.StatsDTO;
import com.xmen.dna.dto.response.VerificationStatsDTO;
import com.xmen.dna.repository.DNAIndividualMutantValidationRepository;

@RunWith(SpringRunner.class)
public class DNAStatsServiceImplTest {

    @Mock
    DNAIndividualMutantValidationRepository repository;

    @InjectMocks
    DNAStatsServiceImpl dnaStatsService;

    @Test
    public void test_getMutant_statistics() {
        when(repository.getMutantsStats("default")).thenReturn(buildListMutantStats());
        VerificationStatsDTO verificationStatsDTO = dnaStatsService.getMutantsStatistics("default");
        assertEquals(10L,verificationStatsDTO.getCount_mutant_dna());
        assertEquals(0L,verificationStatsDTO.getCount_human_dna());
        assertEquals(10L,verificationStatsDTO.getRatio());
    }

    @Test
    public void test_getHuman_statistics() {
        when(repository.getMutantsStats("default")).thenReturn(buildListHumanStats());
        VerificationStatsDTO verificationStatsDTO = dnaStatsService.getMutantsStatistics("default");
        assertEquals(0L,verificationStatsDTO.getCount_mutant_dna());
        assertEquals(10L,verificationStatsDTO.getCount_human_dna());
        assertEquals(0F,verificationStatsDTO.getRatio());
    }

    @Test
    public void test_getFull_statistics() {
        when(repository.getMutantsStats("default")).thenReturn(buildListMutantHumanStats());
        VerificationStatsDTO verificationStatsDTO = dnaStatsService.getMutantsStatistics("default");
        assertEquals(40L,verificationStatsDTO.getCount_mutant_dna());
        assertEquals(100L,verificationStatsDTO.getCount_human_dna());
        assertEquals(0.4F,verificationStatsDTO.getRatio());
    }

    private List<StatsDTO> buildListMutantStats() {
        StatsDTO statsDTO = new StatsDTO("default", 10L,true);
        return Arrays.asList(statsDTO);
    }

    private List<StatsDTO> buildListHumanStats() {
        StatsDTO statsDTO = new StatsDTO("default", 10L,false);
        return Arrays.asList(statsDTO);
    }

    private List<StatsDTO> buildListMutantHumanStats() {
        StatsDTO statsDTO = new StatsDTO("default", 40L,true);
        StatsDTO statsDTO1 = new StatsDTO("default", 100L,false);
        return Arrays.asList(statsDTO,statsDTO1);
    }


}