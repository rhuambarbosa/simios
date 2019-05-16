package br.com.rbs.simios.controller;

import br.com.rbs.simios.service.SimianService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SimianControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SimianController simianControllerMock;

    @Mock
    private SimianService simianServiceyMock;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(simianControllerMock)
                .build();
    }

    @Test
    public void isSimianTrueTest() throws Exception {
        when(simianServiceyMock.isSimian(any())).thenReturn(Boolean.TRUE);

        mockMvc.perform(
                post("/simian")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"dna\": [\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void isSimianFalseTest() throws Exception {
        when(simianServiceyMock.isSimian(any())).thenReturn(Boolean.FALSE);
        mockMvc.perform(
                post("/simian")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"dna\": [\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void isSimianInvalidDNATest() throws Exception {
        mockMvc.perform(
                post("/simian")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"dna\": [\"ATGCGA\", \"CAGTC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isSimianErrorTest() throws Exception {
        when(simianServiceyMock.isSimian(any())).thenThrow(new Exception());
        mockMvc.perform(
                post("/simian")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"dna\": [\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"))
                .andExpect(status().isInternalServerError());
    }
}