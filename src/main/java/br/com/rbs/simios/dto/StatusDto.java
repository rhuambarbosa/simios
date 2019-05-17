package br.com.rbs.simios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusDto {

    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;

    @JsonProperty("count_human_dna")
    private Long countHumanDna;

    @JsonProperty("ratio")
    private double ratio;

    public StatusDto(Long countMutantDna, Long countHumanDna) {
        this.countMutantDna = countMutantDna - 1;
        this.countHumanDna = countHumanDna - 1;
        this.ratio = calculatePercentage();
    }

    private double calculatePercentage() {
        if (this.countMutantDna == 0
                || this.countHumanDna == 0) {
            return 0.0;
        }

        return (this.countMutantDna * 100 / this.countHumanDna);
    }
}