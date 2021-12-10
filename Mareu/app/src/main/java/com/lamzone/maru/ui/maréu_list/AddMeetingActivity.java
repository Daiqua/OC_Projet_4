package com.lamzone.maru.ui.maréu_list;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

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
    private List<Attendee> mAttendeesList = new ArrayList<>();
    private Attendee newAttendee;
    private RecyclerView mRecyclerView;
    private AddMeetingAttendeesListRecyclerViewAdapter mAddMeetingAttendeesListRVAdapter;

    //variable to create a new meeting
    private Meeting newMeeting;
    private String strNewMeetingName;
    private List<Attendee> newMeetingAttendeesList;
    private MeetingRoom roomForTheNewMeeting;
    private String strRoomName;
    private String strMeetingStartDate;
    private String strMeetingHour;
    private int iMeetingDuration;

    //for spinner room list
    private String selectedMeetingRoom;
    private String[] meetingRoomsList;

    //to manage date not filled in layout inputs
    private String empty = "empty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getApiService();
        loadView();
        loadSpinnerList();
        setClickOnSaveButton();
        setClickOnAddAttendeeButton();
    }

    private void loadView() {
        setContentView(R.layout.activity_add_meeting);
        mRecyclerView = findViewById(R.id.activity_add_meetings_attendee_list);

        meetingTopicInput = findViewById(R.id.activity_add_meeting_topic_input);
        setTextWatcher(meetingTopicInput);

        meetingDurationInput = findViewById(R.id.activity_add_meeting_duration_input);
        setTextWatcher(meetingDurationInput);

        meetingAttendeesInput = findViewById(R.id.activity_add_meeting_attendees_input);
        setTextWatcher(meetingAttendeesInput);

        meetingDatePicker = findViewById(R.id.activity_add_meeting_date_picker);

        meetingTimePicker = findViewById(R.id.activity_add_meeting_time_picker);
        meetingTimePicker.setIs24HourView(true);

        Toolbar toolbar = findViewById(R.id.activity_add_meeting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveButton = findViewById(R.id.activity_add_meeting_save_button);
        addAttendeeButton = findViewById(R.id.activity_add_meeting_add_attendee);
        addAttendeeButton.setEnabled(false);
    }

    private void setTextWatcher(TextInputLayout textInputLayout) {
        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                saveButton.setEnabled(true);
                if (!(textInputLayout.getEditText().getText().toString().equals(""))) {
                    if (textInputLayout == meetingAttendeesInput) {
                        addAttendeeButton.setEnabled(true);
                        addAttendeeButton.setImageDrawable(getResources()
                                        .getDrawable(R.drawable.ic_baseline_person_add_24_green));
                    }
                } else {
                    if (textInputLayout == meetingAttendeesInput) {
                        addAttendeeButton.setEnabled(false);
                        addAttendeeButton.setImageDrawable(getResources()
                                            .getDrawable(R.drawable.ic_baseline_person_add_24_grey));
                    }
                }
            }
        });
    }

    private void setClickOnSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load data in newMeeting
                //record meeting room - if condition to manage if not selected
                if (selectedMeetingRoom.equals("")){
                    strRoomName = empty;
                }else {strRoomName = selectedMeetingRoom;}

                //getMonth()+1 needed during conversion from mm to MMMM)
                strMeetingStartDate = meetingDatePicker.getYear() + "."
                        + (meetingDatePicker.getMonth() + 1) + "."
                        + meetingDatePicker.getDayOfMonth();//date format: yyyy.MM.dd
                strMeetingHour = meetingTimePicker.getCurrentHour() + ":" + meetingTimePicker.getCurrentMinute();

                //record meeting duration - if condition to manage if not filled
                if (meetingDurationInput.getEditText().getText().toString().equals("")){
                    iMeetingDuration= 0;
                }else{
                    iMeetingDuration = Integer.parseInt(meetingDurationInput.getEditText().getText().toString());
                }
                //generate the MeetingRoom
                roomForTheNewMeeting = new MeetingRoom(strRoomName, strMeetingStartDate, strMeetingHour, iMeetingDuration);
                // record attendees list - if condition to manage if not filled
                if (mAttendeesList.size()==0) {
                    mAttendeesList.add(new Attendee());
                    newMeetingAttendeesList = mAttendeesList;
                }else{newMeetingAttendeesList = mAttendeesList;}

                //record Topic/name - if condition to manage if not filled
                if (meetingTopicInput.getEditText().getText().toString().equals("")){
                    strNewMeetingName = empty;
                }else {strNewMeetingName = meetingTopicInput.getEditText().getText().toString();}

                newMeeting = new Meeting(strNewMeetingName, roomForTheNewMeeting, newMeetingAttendeesList);
                //check if data are correctly filled
                if (checkIfMeetingDataFilled(newMeeting)){
                    mApiService.addMeeting(newMeeting);
                    finish();
                }else {
                    saveButton.setText("Renseigner tous les champs pour pouvoir valider");
                    saveButton.setEnabled(false);
                }
                //TODO: layout after adding meeting is abnormal
            }
        });
    }

    private void setClickOnAddAttendeeButton() {
        addAttendeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newAttendee = new Attendee(meetingAttendeesInput.getEditText().getText().toString());
                mAttendeesList.add(newAttendee);
                meetingAttendeesInput.getEditText().setText("");

                //RV launch
                mRecyclerView = (RecyclerView) findViewById(R.id.activity_add_meetings_attendee_list);
                mAddMeetingAttendeesListRVAdapter =
                        new AddMeetingAttendeesListRecyclerViewAdapter(mAttendeesList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                mRecyclerView.setAdapter(mAddMeetingAttendeesListRVAdapter);
            }
        });
    }

    private void loadSpinnerList() {
        meetingRoomsList = mApiService.getRoomsList();
        Spinner meetingRoomSpinner = findViewById(R.id.activity_add_meeting_room_spinner);
        meetingRoomSpinner.setOnItemSelectedListener(this);
        ArrayAdapter meetingRoomArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meetingRoomsList);
        meetingRoomArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingRoomSpinner.setAdapter(meetingRoomArrayAdapter);
    }

    private boolean checkIfMeetingDataFilled(Meeting meeting){
        if (meeting.getMeetingRoom().getStrMeetingRoomName().equals(empty)
                || meeting.getMeetingRoom().getMeetingDuration()==0
                || meeting.getStrMeetingName().equals(empty)
                || meeting.getMeetingAttendeesList().get(0).equals(new Attendee())){
            meeting.getMeetingAttendeesList().clear();
            return false;
        }else{ return true;}
    }

    //for spinner room list
    //TODO add pad listener for enter action
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedMeetingRoom = meetingRoomsList[position];
        saveButton.setEnabled(true);
    }

    //for spinner room list
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

