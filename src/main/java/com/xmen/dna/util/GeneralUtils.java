package com.xmen.dna.util;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import com.xmen.dna.exception.SquareMatrixException;

/**
 * Bunch of string utilities
 * @author fr.rodriguez
 */
public class GeneralUtils {

    private GeneralUtils() {}

    /**
     * Joins an string array contest as a single string
     * @param stringArray
     * @return
     */
    public static String getStringFromStringArray(String[] stringArray) {
        StringJoiner joiner = new StringJoiner("");
        if(stringArray != null && stringArray.length>0) {
            for (String singleString : stringArray) {
                joiner.add(singleString);
            }
        }
        return joiner.toString();
    }

    /**
     * Calculates a ratio from 2 values.
     * @param principalCount
     * @param secundaryCount
     * @return principal/secundary
     */
    public static Float calculateRatio(Long principalCount, Long secundaryCount) {

        principalCount = principalCount != null ? principalCount:0L;

        if(secundaryCount == null || secundaryCount.equals(0L) ) {
            return Float.valueOf(principalCount);
        }else {
            return Float.valueOf(principalCount)/Float.valueOf(secundaryCount);
        }
    }

    /**
     *
     * @param dna
     * @return
     */
    public static char[][] initSquareMatrix(String[] stringArray, Pattern pattern) throws SquareMatrixException {

        if (stringArray == null) {
            throw new SquareMatrixException("not able to create NxN matrix - you must especify a dna sequence!");
        }

        if (pattern == null) {
            throw new SquareMatrixException("not able to create NxN matrix - you must especify a pattern!");
        }

        int n = stringArray.length;
        char[][] chars = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] validatedChars = stringArray[i].toCharArray();
            if (validatedChars.length != n || !pattern.matcher(stringArray[i]).matches()) {
                throw new SquareMatrixException("not able to create NxN matrix");
            } else {
                chars[i] = validatedChars;
            }
        }

        return chars;
    }

    /**
     * concatenates charRoGenerate k times
     * @param charTogenerate
     * @param k
     * @return
     */
    public static String generate(Character charTogenerate, int k) {
        String generated ="";
        for(int i=0; i<k; i++){
            generated += charTogenerate;
        }
        return generated;
    }

}
