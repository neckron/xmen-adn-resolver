package com.xmen.dna.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import com.xmen.dna.dto.request.DNARequestDTO;
import com.xmen.dna.dto.response.DNAResponseDTO;
import com.xmen.dna.service.DNAResolverService;

@RunWith(SpringRunner.class)
@WebMvcTest(DNAMutantValidationController.class)
public class DNAMutantValidationControllerTest {

    public static final String API_URL = "/mutant";

    @MockBean
    DNAResolverService dnaResolverService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_isMutant_is_mutant() throws Exception {
        when(dnaResolverService.isMutant(any(DNARequestDTO.class)))
                .thenReturn(DNAResponseDTO.builder().isMutant(true).message("Mutant founded!").build());

        ResultActions result = mockMvc.perform(post("/mutant")
                .content("{\"dna\": [\"ACGT\",\"ACGT\"]}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.isMutant").value(true))
                .andExpect(jsonPath("$.message").value("Mutant founded!"));
    }

    @Test
    public void test_isMutant_not_mutant() throws Exception {
        when(dnaResolverService.isMutant(any(DNARequestDTO.class)))
                .thenReturn(DNAResponseDTO.builder().isMutant(false).message("No Mutant founded!").build());

        ResultActions result = mockMvc.perform(post("/mutant")
                .content("{\"dna\": [\"CCAT\",\"ACGT\"]}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.isMutant").value(false))
                .andExpect(jsonPath("$.message").value("No Mutant founded!"));
    }

    @Test
    public void test_isMutant_dna_comes_null() throws Exception {
        when(dnaResolverService.isMutant(any(DNARequestDTO.class)))
                .thenReturn(DNAResponseDTO.builder().isMutant(false).message("No Mutant founded!").build());

        ResultActions result = mockMvc.perform(post("/mutant")
                .content("{\"dna\": null }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.isMutant").value(false))
                .andExpect(jsonPath("$.message").value("You should specify a valid dna sequence"));
    }

    @Test
    public void test_isMutant_dna_comes_empty() throws Exception {
        when(dnaResolverService.isMutant(any(DNARequestDTO.class)))
                .thenReturn(DNAResponseDTO.builder().isMutant(false).message("No Mutant founded!").build());

        ResultActions result = mockMvc.perform(post("/mutant")
                .content("{\"dna\": []}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.isMutant").value(false))
                .andExpect(jsonPath("$.message").value("You should specify a valid dna sequence"));
    }

}