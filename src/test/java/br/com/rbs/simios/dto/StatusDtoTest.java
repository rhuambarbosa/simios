package br.com.rbs.simios.dto;

import org.junit.Test;

import static org.testng.Assert.assertEquals;

public class StatusDtoTest {

    @Test
    public void statusDtoTest() {
        StatusDto statusDto = new StatusDto(40l, 100l);
        double ratioExpected = 40;
        assertEquals(ratioExpected, statusDto.getRatio(), "% não corresponde ao esperado.");
    }

    @Test
    public void statusDtoMutant0Test() {
        StatusDto statusDto = new StatusDto(0l, 100l);
        double ratioExpected = 0.0;
        assertEquals(ratioExpected, statusDto.getRatio(), "% não corresponde ao esperado.");
    }

    @Test
    public void statusDtoHuman0Test() {
        StatusDto statusDto = new StatusDto(40l, 0l);
        double ratioExpected = 0.0;
        assertEquals(ratioExpected, statusDto.getRatio(), "% não corresponde ao esperado.");
    }



}