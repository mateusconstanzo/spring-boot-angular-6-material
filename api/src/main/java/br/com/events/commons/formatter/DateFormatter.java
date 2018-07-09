package br.com.events.commons.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface DateFormatter {

    static DateFormat getDateFormatter() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    static DateFormat getDateHourFormatter() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }

    static DateFormat getHourFormatter() {
        return new SimpleDateFormat("HH:mm");
    }

}
