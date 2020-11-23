package com.xmen.dna.util;

import java.util.StringJoiner;

public class GeneralUtils {

    private GeneralUtils() {}

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

    public static Float calculateRatio(Long principalCount, Long secundaryCount) {

        principalCount = principalCount != null ? principalCount:0L;

        if(secundaryCount == null || secundaryCount.equals(0L) ) {
            return Float.valueOf(principalCount);
        }else {
            return Float.valueOf(principalCount)/Float.valueOf(secundaryCount);
        }
    }

}
