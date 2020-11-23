package com.xmen.dna.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.xmen.dna.dto.response.VerificationStatsDTO;
import com.xmen.dna.service.DNAStatsService;

@RunWith(SpringRunner.class)
@WebMvcTest(DNAStatsController.class)
public class DNAStatsControllerTest {

    public static final String API_URL = "/stats";

    @MockBean
    DNAStatsService dnaStatsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_getStats_algorithmName_null() throws Exception {
        VerificationStatsDTO verificationStatsDTO = new VerificationStatsDTO();
        verificationStatsDTO.setCount_mutant_dna(40L);
        verificationStatsDTO.setCount_human_dna(100L);
        verificationStatsDTO.setRatio(0.4F);
        when(dnaStatsService.getMutantsStatistics(null))
                .thenReturn(verificationStatsDTO);

        ResultActions result = mockMvc.perform(get(API_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna").value(40L))
                .andExpect(jsonPath("$.count_human_dna").value(100L))
                .andExpect(jsonPath("$.ratio").value(0.4F));
    }

    @Test
    public void test_getStats_algorithmName_not_null() throws Exception {
        VerificationStatsDTO verificationStatsDTO = new VerificationStatsDTO();
        verificationStatsDTO.setCount_mutant_dna(40L);
        verificationStatsDTO.setCount_human_dna(100L);
        verificationStatsDTO.setRatio(0.4F);
        when(dnaStatsService.getMutantsStatistics("default"))
                .thenReturn(verificationStatsDTO);

        ResultActions result = mockMvc.perform(get(API_URL)
                .param("algorithmName","default")
                .accept(MediaType.APPLICATION_JSON_VALUE));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna").value(40L))
                .andExpect(jsonPath("$.count_human_dna").value(100L))
                .andExpect(jsonPath("$.ratio").value(0.4F));
    }

}