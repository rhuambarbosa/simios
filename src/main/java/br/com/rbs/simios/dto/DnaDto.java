package br.com.rbs.simios.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.stream.Stream;

@Data
public class DnaDto {

    String[] dna;

    public DnaDto() {

    }

    public DnaDto(final String[] dna) {
        this.dna = dna;
    }

    public boolean isValid() {
        boolean status = false;

        if (dna != null) {
            int tamanhoMatriz = dna[0].length();

            if (dna.length == tamanhoMatriz) {
                Stream<String> stream = Arrays.stream(dna).filter(x -> x.length() != tamanhoMatriz);
                if (stream.count() == 0) {
                    status = Boolean.TRUE;
                }
            }
        }

        return status;
    }
}
