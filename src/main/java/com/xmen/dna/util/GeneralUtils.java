package com.xmen.dna.util;

import java.util.StringJoiner;

public class GeneralUtils {

    /**
     *
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

}
