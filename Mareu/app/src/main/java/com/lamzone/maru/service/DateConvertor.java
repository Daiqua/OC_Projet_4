package com.lamzone.maru.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateConvertor {

    private static final SimpleDateFormat dateFormatter_yyyy_MM_dd = new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE);
    private static final SimpleDateFormat dateFormatter_dd_MMMM = new SimpleDateFormat("dd MMMM", Locale.FRANCE);
    private static Date date = new Date();

    public static String convert_yyyy_MM_dd_to_dd_MMMM(String strDatePattern_yyyy_MM_dd) {
        try {
            date = dateFormatter_yyyy_MM_dd.parse(strDatePattern_yyyy_MM_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return dateFormatter_dd_MMMM.format(date);
    }

    //TODO: remove if not used at the end of the project
    public static String convert_dd_MMMM_to_yyyy_MM_dd(String strDatePattern_dd_MMMM) {
        try {
            date = dateFormatter_dd_MMMM.parse(strDatePattern_dd_MMMM);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return dateFormatter_yyyy_MM_dd.format(date);
    }

    public static Date convert_yyyy_MM_dd_to_Date(String strDatePattern_yyyy_MM_dd) {
        try {
            date = dateFormatter_yyyy_MM_dd.parse(strDatePattern_yyyy_MM_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
