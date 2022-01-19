package com.lamzone.maru.ui.maréu_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.service.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MaReuApiService mApiService;

    TextInputLayout meetingTopicInput;
    TextInputLayout meetingDurationInput;
    TextInputLayout meetingAttendeesInput;
    ImageButton addAttendeeButton;
    DatePicker meetingDatePicker;
    TimePicker meetingTimePicker;
    MaterialButton saveButton;

    //variable to manage the creation of the attendees list
    private final List<Attendee> mAttendeesList = new ArrayList<>();
    private Attendee newAttendee;
    private RecyclerView mRecyclerView;
    private AddMeetingAttendeesListRecyclerViewAdapter mAddMeetingAttendeesListRVAdapter;

    //for spinner room list
    private MeetingRoom selectedMeetingRoom;
    private List<MeetingRoom> mMeetingRoomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getApiService();
        loadView();
        loadSpinnerList();
        loadRecyclerView();
        setClickOnSaveButton();
        setClickOnAddAttendeeButton();
    }

    private void loadRecyclerView() {
        mRecyclerView = findViewById(R.id.activity_add_meetings_attendee_list);
        mAddMeetingAttendeesListRVAdapter =
                new AddMeetingAttendeesListRecyclerViewAdapter(mAttendeesList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAddMeetingAttendeesListRVAdapter);
    }

    private void loadView() {
        setContentView(R.layout.activity_add_meeting);
        mRecyclerView = findViewById(R.id.activity_add_meetings_attendee_list);
        meetingTopicInput = findViewById(R.id.activity_add_meeting_topic_input);
        meetingDurationInput = findViewById(R.id.activity_add_meeting_duration_input);
        meetingAttendeesInput = findViewById(R.id.activity_add_meeting_attendees_input);
        saveButton = findViewById(R.id.activity_add_meeting_save_button);
        addAttendeeButton = findViewById(R.id.activity_add_meeting_add_attendee);
        meetingDatePicker = findViewById(R.id.activity_add_meeting_date_picker);
        meetingTimePicker = findViewById(R.id.activity_add_meeting_time_picker);
        meetingTimePicker.setIs24HourView(true);

        Toolbar toolbar = findViewById(R.id.activity_add_meeting_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //set all text watchers
        TextInputLayout[] textInputLayoutList = {meetingTopicInput, meetingTopicInput, meetingAttendeesInput};
        Util.setTextWatcher(textInputLayoutList, this);
    }

    private void setClickOnSaveButton() {
        saveButton.setOnClickListener(v -> {
            @SuppressLint("DefaultLocale") Meeting newMeeting = new Meeting(
                    Objects.requireNonNull(meetingTopicInput.getEditText()).getText().toString(),
                    selectedMeetingRoom,
                    mAttendeesList,
                    meetingDatePicker.getYear() + "." + (meetingDatePicker.getMonth() + 1) + "."
                            + meetingDatePicker.getDayOfMonth(),
                    meetingTimePicker.getCurrentHour() + ":" + String.format("%02d", meetingTimePicker.getCurrentMinute()),
                    Integer.parseInt(Objects.requireNonNull(meetingDurationInput.getEditText()).getText().toString()));
            mApiService.addMeeting(newMeeting);
            this.finish();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setClickOnAddAttendeeButton() {
        addAttendeeButton.setOnClickListener(v -> {
            newAttendee = new Attendee(Objects.requireNonNull(meetingAttendeesInput.getEditText()).getText().toString());
            mAttendeesList.add(newAttendee);
            meetingAttendeesInput.getEditText().setText("");
            mAddMeetingAttendeesListRVAdapter.notifyDataSetChanged();

        });
    }

    private void loadSpinnerList() {
        String[] meetingRoomsList = mApiService.getRoomsList();
        Spinner meetingRoomSpinner = findViewById(R.id.activity_add_meeting_room_spinner);
        meetingRoomSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> meetingRoomArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meetingRoomsList);
        meetingRoomArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingRoomSpinner.setAdapter(meetingRoomArrayAdapter);
    }

    //for spinner room list
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMeetingRoomList = mApiService.getMeetingRooms();
        selectedMeetingRoom = mMeetingRoomList.get(position);//MeetingRoom
        checkDataIsCorrectlyFilled();
    }

    //for spinner room list
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedMeetingRoom = mMeetingRoomList.get(0);
    }

    @SuppressLint("SetTextI18n")
    public void checkDataIsCorrectlyFilled() {
        if (TextUtils.isEmpty(Objects.requireNonNull(meetingTopicInput.getEditText()).getText().toString())
                || TextUtils.isEmpty(meetingTopicInput.getEditText().getText().toString())
                || mAddMeetingAttendeesListRVAdapter.getItemCount() == 0
                || selectedMeetingRoom.equals(mMeetingRoomList.get(0))) {
            saveButton.setEnabled(false);
            saveButton.setText("Renseigner tous les champs pour pouvoir valider");

        } else {
            saveButton.setEnabled(true);
            saveButton.setText("Ajouter la réunion");
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void checkAttendeeInputToSetAddAttendeeButton() {
        if (TextUtils.isEmpty(Objects.requireNonNull(meetingAttendeesInput.getEditText()).getText().toString())) {
            addAttendeeButton.setEnabled(false);
            addAttendeeButton.setImageDrawable(getResources()
                    .getDrawable(R.drawable.ic_baseline_person_add_24_grey));
        } else {
            addAttendeeButton.setEnabled(true);
            addAttendeeButton.setImageDrawable(getResources()
                    .getDrawable(R.drawable.ic_baseline_person_add_24_green));
        }
    }
}

