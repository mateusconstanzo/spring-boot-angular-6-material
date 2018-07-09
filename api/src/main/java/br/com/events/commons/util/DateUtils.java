package br.com.events.commons.util;

import br.com.events.commons.formatter.DateFormatter;

import java.text.ParseException;
import java.util.Date;

public interface DateUtils {

    static Date parse(String date) throws ParseException {
        return DateFormatter.getDateHourFormatter().parse(date);
    }

    static String formatDate(Date date) throws ParseException {
        return DateFormatter.getDateFormatter().format(date);
    }

    static String formatHour(Date date) throws ParseException {
        return DateFormatter.getHourFormatter().format(date);
    }


}
