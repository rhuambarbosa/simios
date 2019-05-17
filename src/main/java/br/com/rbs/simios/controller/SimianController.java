package br.com.rbs.simios.controller;

import br.com.rbs.simios.dto.DnaDto;
import br.com.rbs.simios.service.SimianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimianController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimianController.class);

    @Autowired
    private SimianService simianService;

    @PostMapping(value = "/simian")
    public ResponseEntity<?> isSimian(@RequestBody @Validated DnaDto dnaDto) {
        LOGGER.info("SimianController:Recebendo requisição de verificação de cadeia de DNA:{}", dnaDto.getDna());

        HttpStatus status = HttpStatus.OK;

        try {
            LOGGER.info("SimianController:Solicitação da verificicação cadeia de DNA");
            boolean isSimian = false;

            if (dnaDto.isValid()) {
                isSimian = simianService.isSimian(dnaDto.getDna());
                if (!isSimian) {
                    status = HttpStatus.FORBIDDEN;
                }
            } else {
                status = HttpStatus.BAD_REQUEST;
            }

            LOGGER.info("SimianController:Cadeia de DNA:{} verificada. IS SIMIAN:{}", dnaDto.getDna(), isSimian);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.error("SimianController:Falha ao validar cadeia de DNA", e);
        }

        LOGGER.info("SimianController:Respondendo requisição de verificação de cadeia de DNA");
        return new ResponseEntity(status);
    }
}
