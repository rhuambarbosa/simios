package br.com.rbs.simios.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Data
public class DnaDto {

    private static final Pattern NUMBER = Pattern.compile("[*0-9]");
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]");
    private static final Pattern INVALID_CHARACTERS = Pattern.compile("B|D|E|F|H|I|J|K|L|M|N|O|P|Q|R|S|U|V|X|Z");

    String[] dna;

    public DnaDto(final String[] dna) {
        this.dna = dna;
    }

    public boolean isValid() {
        String dnaStr = getDnaStr(this.dna);

        if (NUMBER.matcher(dnaStr).find()
                || SPECIAL_CHARACTERS.matcher(dnaStr).find()
                || INVALID_CHARACTERS.matcher(dnaStr).find()) {
            return false;
        }

        return validLength(this.dna);
    }

    private String getDnaStr(String[] dna) {
        String dnaStr = "";
        for (int i = 0; i < dna.length; i++)
            dnaStr += dna[i];

        return dnaStr;
    }

    private boolean validLength(String[] dna) {
        boolean status = false;

        if (dna != null) {
            int tamanhoMatriz = dna[0].length();

            if (dna.length == tamanhoMatriz) {
                Stream<String> stream = Arrays.stream(dna).filter(x -> x.length() != tamanhoMatriz);
                status = stream.count() == 0;
            }
        }

        return status;
    }
}
