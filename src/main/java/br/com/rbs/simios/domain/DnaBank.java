package br.com.rbs.simios.domain;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class DnaBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dnaIdx;
    private String dna;
    private boolean simian;

    public DnaBank() {
    }

    public DnaBank(String[] dna, boolean isSimian) {
        this.dnaIdx = dna[0];
        this.dna = new Gson().toJson(dna);
        this.simian = isSimian;
    }
}
