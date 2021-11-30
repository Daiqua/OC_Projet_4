package com.lamzone.maru.ui.maréu_list;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.lamzone.maru.MaReuActivity;
import com.lamzone.maru.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFragment extends DialogFragment {

    Button validationButton;
    DatePicker mDatePicker;

    private String strDateFiltered;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public static DatePickerFragment newInstance() {
        DatePickerFragment fragment = new DatePickerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        mDatePicker = view.findViewById(R.id.fragment_date_picker);
        validationButton = view.findViewById(R.id.fragment_date_picker_validation_button);
        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getMonth()+1 needed during conversion from mm to MMMM
               strDateFiltered = mDatePicker.getYear()+"."+(mDatePicker.getMonth()+1)+"."+mDatePicker.getDayOfMonth();//date format: yyyy.MM.dd
                MaReuActivity.setStrDateFiltered(strDateFiltered);
                MaReuActivity.setIsDateFilterActivated(true);

                //TODO: add interface and listener
                Intent intent = new Intent(getActivity(), MaReuActivity.class);
                startActivity(intent);
                dismiss();
            }
        });
        return view;
    }
}