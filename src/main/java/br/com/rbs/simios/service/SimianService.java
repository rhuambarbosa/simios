package br.com.rbs.simios.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimianService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimianService.class);

    public boolean isSimian(String[] dna) throws Exception {
        boolean isSimian = verify(dna);

        if(!isSimian){

        }



        return false;
    }

    private boolean verifyV(String[] dna){
        //Fazer a conversão para vertical e enviar para validação
        return  verify(dna);
    }

    private boolean verifyDED(String[] dna){
        //Fazer a conversão para vertical e enviar para validação
        return  verify(dna);
    }

    private boolean verifyDDE(String[] dna){
        //Fazer a conversão para vertical e enviar para validação
        return  verify(dna);
    }

    private boolean verify(String[] dna){



        return  false;
    }
}
