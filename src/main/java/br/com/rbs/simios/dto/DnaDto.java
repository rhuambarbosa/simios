package br.com.rbs.simios.dto;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Data
public class DnaDto {

    private static final Pattern PATTERN = Pattern.compile("^A|^T|^C|^G");
    private static final Pattern NUMBER = Pattern.compile("[*0-9]");
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]");

    String[] dna;


    public DnaDto(final String[] dna) {
        this.dna = dna;
    }

    public boolean isValid() {
        String dnaJson = new Gson().toJson(dna);

        if (NUMBER.matcher(dnaJson).find()) {
            return false;
        } else if (SPECIAL_CHARACTERS.matcher(dnaJson).find()) {
            return false;
        }

        return validLength(this.dna);
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
