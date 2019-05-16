package br.com.rbs.simios.dto;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DnaDtoTest {

    @Test
    public void isValidTrueTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
        DnaDto dnaDto = new DnaDto(dna);
        assertTrue("Cadeia correta falhou", dnaDto.isValid());
    }

    @Test
    public void isValidFalseColumnTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA"};
        DnaDto dnaDto = new DnaDto(dna);
        assertFalse("Validação errada da coluna", dnaDto.isValid());
    }

    @Test
    public void isValidFalseQtdTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGG", "CCCCTA", "TCACTG"};
        DnaDto dnaDto = new DnaDto(dna);
        assertFalse("Validação errrada dos valores dentro da coluna",dnaDto.isValid());
    }
}