package br.com.rbs.simios.service;

import br.com.rbs.simios.repository.DnaBankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DnaBankServiceTest {

    @InjectMocks
    private DnaBankService dnaBankServiceMock;

    @Mock
    private DnaBankRepository dnaBankRepositoryMock;

    @Test
    public void saveHumanTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCTCTA", "TCAAAA"};
        dnaBankServiceMock.addDna(dna, false);
        verify(dnaBankRepositoryMock, times(1)).addHumanDna();
    }

    @Test
    public void saveMutantTest() {
        String[] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCTCTA", "TCAAAA"};
        dnaBankServiceMock.addDna(dna, true);
        verify(dnaBankRepositoryMock, times(1)).addMutantDna();
    }
}