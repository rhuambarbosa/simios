package br.com.rbs.simios.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimianServiceTest {

    @InjectMocks
    private SimianService simianServiceMock;

    @Mock
    private DnaBankService dnaBankService;


    @Test
    public void simianHATest() throws Exception {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCTCTA", "TCAAAA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("AAAA deveria ser encontrado", isSimian);
    }

    @Test
    public void simianHTTest() throws Exception {
        String[] dna = {"CTGAGA", "CTTTTC", "TATTGT", "AGAGGG", "CCTCTA", "TCAACA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("TTTT deveria ser encontrado", isSimian);
    }

    @Test
    public void simianHCTest() throws Exception {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCTAAA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("CCCC deveria ser encontrado", isSimian);
    }

    @Test
    public void simianHGTest() throws Exception {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGGGGG", "CCTCTA", "TCAACA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("GGGG deveria ser encontrado", isSimian);
    }

    @Test
    public void simianVATest() throws Exception {
        String[] dna = {"ATGAGA", "ATGAGC", "AATTGT", "AGAGGG", "CCTCTA", "TCCAAA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("V-AAAA deveria ser encontrado", isSimian);
    }

    @Test
    public void simianVCTest() throws Exception {
        String[] dna = {"CTGCGA", "ATGACC", "AATCGT", "AGACGG", "CCTCTA", "TCCCAA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("V-CCCC deveria ser encontrado", isSimian);
    }

    @Test
    public void simianVGTest() throws Exception {
        String[] dna = {"ATGAGA", "ATGAGC", "TATTGT", "AGAGGG", "CCTCGA", "TCCAGA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("V-GGGG deveria ser encontrado", isSimian);
    }

    @Test
    public void simianVTTest() throws Exception {
        String[] dna = {"TTGAGA", "ATGAGC", "ATTTGT", "ATAGGG", "CCTCTA", "TCCAAA"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("V-TTTT deveria ser encontrado", isSimian);
    }

    @Test
    public void simianDLR4X4GTest() throws Exception {
        String[] dna = {"GAGA", "GGGC", "TTGT", "AGCG"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("DLR4X4-GGGG deveria ser encontrado", isSimian);
    }

    @Test
    public void simianDLR4X4GInvalidTest() throws Exception {
        String[] dna = {"GAGA", "AGGC", "TTGT", "AGCT"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertFalse("DLR4X4-GGGG deveria ser encontrado", isSimian);
    }

    @Test
    public void simianDLR5X5TTest() throws Exception {
        String[] dna = {"GGAGA", "GTGAC", "TATGT", "ATGCG", "ATGCG"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("DLR5X5-GGGG deveria ser encontrado", isSimian);
    }

    @Test
    public void simianDRL5X5TTest() throws Exception {
        String[] dna = {"ATCGT", "TACTT", "CGTGG", "GTGAA", "TGCAT"};

        when(dnaBankService.isSimian(any())).thenReturn(null);
        boolean isSimian = simianServiceMock.isSimian(dna);
        assertTrue("DRL5X5-TTTT deveria ser encontrado", isSimian);
    }
}