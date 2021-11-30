package com.lamzone.maru.service;

import androidx.appcompat.app.AppCompatActivity;

import com.lamzone.maru.model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateConvertor extends AppCompatActivity {

    public static String convert_yyyy_MM_dd_to_dd_MMMM(String strDatePattern_yyyy_MM_dd){
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE);
        try {
            date = dateFormatter.parse(strDatePattern_yyyy_MM_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormatter = new SimpleDateFormat("dd MMMM", Locale.FRANCE);
        String reformatedDateTo_dd_MMMM = dateFormatter.format(date);
        return reformatedDateTo_dd_MMMM;
    }

    public static String convert_dd_MMMM_to_yyyy_MM_dd (String strDatePattern_dd_MMMM){
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM", Locale.FRANCE);
        try {
            date = dateFormatter.parse(strDatePattern_dd_MMMM);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormatter = new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE);
        String reformatedDateTo_yyyy_MM_dd = dateFormatter.format(date);
        return reformatedDateTo_yyyy_MM_dd;
    }

    public static Date convert_yyyy_MM_dd_to_Date(String strDatePattern_yyyy_MM_dd) {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE);
        try {
            date = dateFormatter.parse(strDatePattern_yyyy_MM_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
