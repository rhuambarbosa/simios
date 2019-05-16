package br.com.rbs.simios.service;

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

    public StatusDto geStatus() throws Exception {
        try {
            LOGGER.info("StatusService:Recuperando informações de status");
            final Long humanDna = dnaBankRepository.getCountHumanDna().longValue();
            final Long mutantDna = dnaBankRepository.getCountMutantDna().longValue();
            LOGGER.info("StatusService:Informações de status recuperadas com sucesso");
            return new StatusDto(mutantDna, humanDna);
        } catch (Exception e) {
            LOGGER.error("StatusService:Informações de status recuperadas com sucesso", e);
            throw new Exception("StatusService:Falha ao recuperar status");
        }
    }
}