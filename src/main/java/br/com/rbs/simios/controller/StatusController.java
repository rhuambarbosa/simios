package br.com.rbs.simios.controller;

import br.com.rbs.simios.dto.StatusDto;
import br.com.rbs.simios.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusService statusService;

    @ResponseBody
    @GetMapping(value = "/stats")
    public ResponseEntity<StatusDto> getStatus() {
        LOGGER.info("StatusController:Recebendo requisição de status");

        Object body;
        HttpStatus status = HttpStatus.OK;

        try {
            LOGGER.info("StatusController:Solicitando status");
            body = statusService.geStatus();
            LOGGER.info("StatusController:Status retornado com sucesso");
        } catch (Exception e) {
            body = "Falha interna";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.error("StatusController:Falha ao retorna o status", e);
        }

        LOGGER.info("StatusController:Respondendo requisição de status");
        return new ResponseEntity(body, status);
    }
}
