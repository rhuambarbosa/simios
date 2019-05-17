package br.com.rbs.simios.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SimianService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimianService.class);

    private static final Pattern PATTERN = Pattern.compile("AAAA|TTTT|CCCC|GGGG");

    private static final int QTD_MIN = 4;

    private static final long MIN_SEQ = 2;

    @Autowired
    private DnaBankService dnaBankService;

    public boolean isSimian(String[] dna) throws Exception {
        LOGGER.info("SimianService: Verificano se já existe cadeia de DNA");
        Boolean isSimian = dnaBankService.isSimian(dna);

        if (isSimian == null) {
            LOGGER.info("SimianService: Iniciando a validação da cadeia de DNA");
            isSimian = validarDNA(dna);

            LOGGER.info("SimianService: Validação concluida da cadeia de DNA");

            dnaBankService.addDna(dna, isSimian);

            LOGGER.info("SimianService:Retornando status da validação da cadeia de DNA");
        }
        return isSimian;
    }

    private boolean validarDNA(String[] dna) {
        long isSimian = check(dna);
        if (isSimian >= MIN_SEQ) {
            return Boolean.TRUE;
        }

        isSimian += checkVerticalSequence(dna);
        if (isSimian >= MIN_SEQ) {
            return Boolean.TRUE;
        }

        isSimian += checkDiagonalLeftToRightSequence(dna);
        if (isSimian >= MIN_SEQ) {
            return Boolean.TRUE;
        }

        isSimian += checkDiagonalRightToLeftSequence(dna);

        return isSimian >= MIN_SEQ;
    }

    private long checkVerticalSequence(String[] dna) {
        String[] newDNA = new String[dna.length];

        for (int idx = 0; idx < dna.length; idx++) {
            String cadeia = "";

            for (int i = 0; i < dna.length; i++) {
                cadeia += dna[i].charAt(idx);
            }
            newDNA[idx] = cadeia;
        }

        return check(newDNA);
    }

    private long checkDiagonalLeftToRightSequence(String[] dna) {
        String[] newDNA = getDiagonalSequence(dna);

        if (newDNA != null && newDNA[0] != null)
            return check(newDNA);

        return 0L;
    }

    private String[] getDiagonalSequence(String[] dna) {
        //Recupera qtd de linhas a serem validadas
        int qtdLine = dna.length - QTD_MIN;

        if (qtdLine < 0)
            return null;

        if (qtdLine == 0) {
            //Caso 0 somente uma posicilidade para validação
            qtdLine = 1;
        } else {
            //Caso maior que sero se incrementa duas linhas por possibilidade
            qtdLine += 2;
        }

        List<String> newDNAList = new ArrayList();

        for (int idxCadeiaPrincipal = 0; idxCadeiaPrincipal <= (qtdLine - 1); idxCadeiaPrincipal++) {
            String cadeiaPrincipal = dna[idxCadeiaPrincipal];

            int interacao = 0;
            if (idxCadeiaPrincipal == 0) {
                interacao = dna.length - (QTD_MIN + idxCadeiaPrincipal);
            }

            for (int idx = 0; idx <= interacao; idx++) {
                String cadeia = "";
                cadeia += cadeiaPrincipal.charAt(idx);

                int skipLine = 0;
                int idxLine = idx + 1;
                for (String str : dna) {
                    if ((skipLine++ > idxCadeiaPrincipal) && (idxLine < dna.length)) {
                        cadeia += str.charAt(idxLine++);
                    }
                }
                newDNAList.add(cadeia);
            }
        }

        String[] newArray = new String[newDNAList.size()];
        return newDNAList.toArray(newArray);
    }

    private long checkDiagonalRightToLeftSequence(String[] dna) {
        String[] newDNA = getDiagonalSequence(getRevertDnaSequence(dna));

        if (newDNA != null && newDNA[0] != null)
            return check(newDNA);

        return 0L;
    }

    private String[] getRevertDnaSequence(String[] dna) {
        List<String> reverseList = Arrays.stream(dna)
                .parallel()
                .map(original -> new StringBuilder(original).reverse().toString())
                .collect(Collectors.toList());

        String[] reverse = new String[reverseList.size()];
        return reverseList.toArray(reverse);
    }

    private long check(String[] dna) {
        Stream<String> stream = Arrays.stream(dna).parallel()
                .filter(cadeia -> PATTERN.matcher(cadeia).find());
        return stream.count();
    }
}