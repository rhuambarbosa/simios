package br.com.rbs.simios.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SimianService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimianService.class);

    private static final Pattern PATTERN = Pattern.compile("AAAA|TTTT|CCCC|GGGG");

    private static final int QTD_MIN = 4;

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
        boolean isSimian = check(dna);
        isSimian = checkVerticalSequence(dna, isSimian);
        isSimian = checkDiagonalLeftToRightSequence(dna, isSimian);
        isSimian = checkDiagonalRightToLeftSequence(dna, isSimian);
        return isSimian;
    }

    private boolean checkVerticalSequence(String[] dna, boolean isSimian) {
        if (isSimian)
            return isSimian;

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

    private boolean checkDiagonalLeftToRightSequence(String[] dna, boolean isSimian) {
        if (isSimian)
            return isSimian;

        String[] newDNA = getDiagonalSequence(dna);

        if (newDNA != null && newDNA[0] != null)
            return check(newDNA);

        return isSimian;
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

        int idxNewDNA = 0;
        String[] newDNA = new String[qtdLine];

        for (int idxCadeiaPrincipal = 0; idxCadeiaPrincipal <= (qtdLine - 1); idxCadeiaPrincipal++) {
            String cadeiaPrincipal = dna[idxCadeiaPrincipal];
            for (int idx = 0; idx <= dna.length - (QTD_MIN + idxCadeiaPrincipal); idx++) {
                String cadeia = "";
                cadeia += cadeiaPrincipal.charAt(idx);

                int skipLine = 0;
                int idxLine = idx + 1;
                for (String str : dna) {
                    if (skipLine++ > idxCadeiaPrincipal) {
                        if (idxLine < dna.length) {
                            cadeia += str.charAt(idxLine++);
                        }
                    }
                }

                newDNA[idxNewDNA++] = cadeia;
            }
        }
        return newDNA;
    }

    private boolean checkDiagonalRightToLeftSequence(String[] dna, boolean isSimian) {
        if (isSimian)
            return isSimian;

        String[] newDNA = getDiagonalSequence(getRevertDnaSequence(dna));

        if (newDNA != null && newDNA[0] != null)
            return check(newDNA);

        return isSimian;
    }

    private String[] getRevertDnaSequence(String[] dna) {
        List<String> reverseList = Arrays.stream(dna)
                .parallel()
                .map(original -> new StringBuilder(original).reverse().toString())
                .collect(Collectors.toList());

        String[] reverse = new String[reverseList.size()];
        return reverseList.toArray(reverse);
    }

    private boolean check(String[] dna) {
        String stream = Arrays.stream(dna).parallel()
                .filter(cadeia -> PATTERN.matcher(cadeia).find())
                .findAny()
                .orElse(null);
        return stream != null;
    }
}