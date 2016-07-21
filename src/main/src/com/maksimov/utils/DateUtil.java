package com.maksimov.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 21.07.16.
 */
public class DateUtil {

    public static Date parseDate(String dateToParse) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateToParse);
        } catch (ParseException ignored) {
        }
        return date;
    }

}
