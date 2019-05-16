package br.com.rbs.simios.service;

import br.com.rbs.simios.dto.StatusDto;
import br.com.rbs.simios.repository.DnaBankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class StatusServiceTest {

    @InjectMocks
    private StatusService statusServiceMock;

    @Mock
    private DnaBankRepository dnaBankRepositoryMock;

    @Test
    public void geStatusTest() {
        when(dnaBankRepositoryMock.getCountHumanDna()).thenReturn(new BigInteger("100"));
        when(dnaBankRepositoryMock.getCountMutantDna()).thenReturn(new BigInteger("40"));

        try {
            StatusDto statusDto = statusServiceMock.geStatus();

            Long countMutantDnaExpected = 40l;
            Long countHumanDna = 100l;
            double ratioExpected = 40.0;

            assertEquals(countHumanDna, statusDto.getCountHumanDna(), "countHumanDna não corresponde ao esperado.");
            assertEquals(countMutantDnaExpected, statusDto.getCountMutantDna(), "countMutantDnaExpectednão corresponde ao esperado.");
            assertEquals(ratioExpected, statusDto.getRatio(), "% não corresponde ao esperado.");
        } catch (Exception e) {
            fail("Erro Inesperado");
        }
    }

    @Test(expected = Exception.class)
    public void geStatusFailTest() throws Exception {
        when(dnaBankRepositoryMock.getCountHumanDna()).thenThrow(new SQLException());
        statusServiceMock.geStatus();
    }
}