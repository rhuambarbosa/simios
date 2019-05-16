package br.com.rbs.simios.controller;

import br.com.rbs.simios.dto.StatusDto;
import br.com.rbs.simios.service.StatusService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class StatusControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private StatusController statusControllerMock;

    @Mock
    private StatusService statusServiceyMock;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(statusControllerMock)
                .build();
    }

    @Test
    public void getStatusTest() {
        try {
            when(statusServiceyMock.geStatus()).thenReturn(new StatusDto(40l, 100l));

            mockMvc.perform(
                    get("/stats")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(content().json("{\"count_mutant_dna\":40,\"count_human_dna\":100,\"ratio\":40.0}"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail("Erro Inesperado");
        }
    }

    @Test
    public void getStatusFailTest() {
        try {
            when(statusServiceyMock.geStatus()).thenThrow(new Exception());

            mockMvc.perform(
                    get("/stats")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(content().string("Falha interna"))
                    .andExpect(status().isInternalServerError());
        } catch (Exception e) {
            fail("Erro Inesperado");
        }
    }
}