package br.com.rbs.simios.repository;

import br.com.rbs.simios.domain.DnaBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DnaBankRepository extends JpaRepository<DnaBank, Long> {

    @Query(nativeQuery = true, value = "SELECT last_value FROM sq_human_dna")
    BigInteger getCountHumanDna();

    @Query(nativeQuery = true, value = "SELECT last_value FROM sq_mutant_dna")
    BigInteger getCountMutantDna();
}