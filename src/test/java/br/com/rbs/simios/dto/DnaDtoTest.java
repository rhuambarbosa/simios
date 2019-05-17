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


    @Test
    public void isValidNumberTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAG9G", "CCCCTA", "TCACTG"};
        DnaDto dnaDto = new DnaDto(dna);
        assertFalse("Não validou o número", dnaDto.isValid());
    }

    @Test
    public void isValidSpecialCharTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAG^G", "CCCCTA", "TCACTG"};
        DnaDto dnaDto = new DnaDto(dna);
        assertFalse("Não validou o caracter especial", dnaDto.isValid());
    }

    @Test
    public void isValidInvalidCharTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGKG", "CCCCTA", "TCACTG"};
        DnaDto dnaDto = new DnaDto(dna);
        assertFalse("Não validou o caracter especial", dnaDto.isValid());
    }
}