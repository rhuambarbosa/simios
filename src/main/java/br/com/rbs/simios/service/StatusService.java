package br.com.rbs.simios.service;

import br.com.rbs.simios.domain.DnaBank;
import br.com.rbs.simios.dto.StatusDto;
import br.com.rbs.simios.repository.DnaBankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    @Autowired
    private DnaBankRepository dnaBankRepository;

    private static boolean existHuman;

    private static boolean existMutant;


    public StatusDto geStatus() throws Exception {
        try {
            LOGGER.info("StatusService:Recuperando informações de status");
            return new StatusDto(getQtdMutantDna(), getQtdHumanDna());
        } catch (Exception e) {
            LOGGER.error("StatusService:Informações de status recuperadas com sucesso", e);
            throw new Exception("StatusService:Falha ao recuperar status");
        }
    }

    private long getQtdHumanDna() {
        Long qtdHumanDna = dnaBankRepository.getCountHumanDna().longValue();

        if (!existHuman) {
            DnaBank humanDna = dnaBankRepository.findFirstBySimianIsFalse();

            if (humanDna == null) {
                qtdHumanDna = 0L;
            } else {
                existHuman = Boolean.TRUE;
            }
        }

        return qtdHumanDna;
    }

    private long getQtdMutantDna() {
        Long qtdMutantDna = dnaBankRepository.getCountMutantDna().longValue();

        if (!existMutant) {
            DnaBank mutantDna = dnaBankRepository.findFirstBySimianIsTrue();

            if (mutantDna == null) {
                qtdMutantDna = 0L;
            } else {
                existMutant = Boolean.TRUE;
            }
        }

        return qtdMutantDna;
    }
}