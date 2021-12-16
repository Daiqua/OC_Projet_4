package com.lamzone.maru.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static String convertYearMonthNumberDayToDayMonthName(String strYearMonthDay) {
        Date date = new Date();
        try {
            date = (new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE).parse(strYearMonthDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return new SimpleDateFormat("dd MMMM", Locale.FRANCE).format(date);
    }

}
