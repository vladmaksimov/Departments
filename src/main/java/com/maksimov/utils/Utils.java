package com.maksimov.utils;

/**
 * Created on 21.07.16.
 */
public class Utils {

    private static final String SEARCH_LIKE_OPERATOR = "%";

    public static String createSearchString(String s) {
        return SEARCH_LIKE_OPERATOR.concat(s).concat(SEARCH_LIKE_OPERATOR);
    }

}
