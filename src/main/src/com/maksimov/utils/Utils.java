package com.maksimov.utils;

import com.maksimov.utils.factorys.ModelFactory;
import com.mysql.jdbc.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 21.07.16.
 */
public class Utils {

    public static Date parseDate(String dateToParse) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = ModelFactory.createSqlDate(formatter.parse(dateToParse).getTime());
        } catch (ParseException ignored) {
        }
        return date;
    }

    public static Long parseLong(String s) {
        Long result = null;
        if (StringUtils.isEmptyOrWhitespaceOnly(s)) {
            return null;
        }
        try {
            result = Long.parseLong(s);
        } catch (NumberFormatException ignored) {
        }

        return result;
    }

    public static Integer parseInteger(String s) {
        Integer result = null;
        if (StringUtils.isEmptyOrWhitespaceOnly(s)) {
            return null;
        }
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException ignored) {
        }

        return result;
    }

}
