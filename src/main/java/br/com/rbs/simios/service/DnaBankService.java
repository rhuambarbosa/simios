package br.com.rbs.simios.service;

import br.com.rbs.simios.domain.DnaBank;
import br.com.rbs.simios.repository.DnaBankRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaBankService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DnaBankService.class);

    @Autowired
    private DnaBankRepository dnaBankRepository;

    public void addDna(String[] dnaSequence, boolean isSimian) {
        LOGGER.info("DnaBankService: Validação concluida da cadeia de DNA");
        dnaBankRepository.save(new DnaBank(dnaSequence, isSimian));

        LOGGER.info("DnaBankService:Adicionando contador.");
        if (isSimian) {
            dnaBankRepository.addMutantDna();
        } else {
            dnaBankRepository.addHumanDna();
        }
    }

    public Boolean isSimian(String[] dna) {
        String dnaIdx = dna[0];
        String dnaJson = new Gson().toJson(dna);

        DnaBank dnaBank = dnaBankRepository.findByDnaIdxAndDna(dnaIdx, dnaJson);

        return dnaBank != null ? dnaBank.isSimian() : null;

    }
}
