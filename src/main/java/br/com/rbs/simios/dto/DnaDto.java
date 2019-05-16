package br.com.rbs.simios.dto;

import lombok.Data;

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
            //pegar a matriz e interar para que a mesma tenha todo o temanho em todos os locais
        }

        return status;
    }
}
