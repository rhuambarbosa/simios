package br.com.rbs.simios.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DnaBank {

    @Id
    private Long id;

}
