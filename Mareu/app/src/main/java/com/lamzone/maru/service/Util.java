package com.lamzone.maru.service;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;
import com.lamzone.maru.ui.maréu_list.AddMeetingActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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

    public static void setTextWatcher(TextInputLayout[] textInputLayoutList, AddMeetingActivity addMeetingActivity) {
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            Objects.requireNonNull(textInputLayout.getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void afterTextChanged(Editable s) {
                    addMeetingActivity.checkDataIsCorrectlyFilled();
                    addMeetingActivity.checkAttendeeInputToSetAddAttendeeButton();
                }
            });
        }
    }

}
