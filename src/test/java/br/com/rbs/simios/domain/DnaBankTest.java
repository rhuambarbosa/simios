package br.com.rbs.simios.domain;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DnaBankTest {

    @Test
    public void dnaBankTest() {
        String[] dna = {"ATCGT", "TACTT", "CGTGG", "GTGAA", "TGCAT"};

        DnaBank dnaBank = new DnaBank(dna, Boolean.TRUE);

        String dnaIdxExpected = "ATCGT";
        String dnaJsonExpected = "[\"ATCGT\",\"TACTT\",\"CGTGG\",\"GTGAA\",\"TGCAT\"]";
        boolean isSimianExpected = true;

        assertTrue("Falha ao montar o dna", dnaJsonExpected.equals(dnaBank.getDna()));
        assertTrue("Falha ao montar o dnaIdx", dnaIdxExpected.equals(dnaBank.getDnaIdx()));
        assertTrue("Falha ao montar se é simion", isSimianExpected);
    }
}