package com.lamzone.maru.ui.maréu_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.lamzone.maru.MaReuActivity;
import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;
import com.lamzone.maru.service.MaReuApiService;

import java.util.ArrayList;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private MaReuApiService mApiService;

    TextInputLayout meetingTopicInput;
    TextInputLayout meetingDurationInput;
    TextInputLayout meetingAttendeesInput;

    ImageView meetingTopicCheck;
    ImageView meetingRoomCheck;
    ImageView meetingDurationCheck;
    ImageView meetingAttendeesCheck;

    ImageButton addAttendeeButton;

    DatePicker meetingDatePicker;

    TimePicker meetingTimePicker;

    private boolean bFilledCount = false; //used to check if all data are filled - use for the saveButton

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getApiService();

        setContentView(R.layout.activity_add_meeting);
        meetingTopicInput = (TextInputLayout) findViewById(R.id.activity_add_meeting_topic_input);
        meetingDatePicker = findViewById(R.id.activity_add_meeting_date_picker);
        meetingTimePicker = findViewById(R.id.activity_add_meeting_time_picker);
        meetingTimePicker.setIs24HourView(true);

        //for spinner room list
        meetingRoomsList = mApiService.getRoomsList();
        Spinner meetingRoomSpinner = findViewById(R.id.activity_add_meeting_room_spinner);
        meetingRoomSpinner.setOnItemSelectedListener(this);
        ArrayAdapter meetingRoomArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meetingRoomsList);
        meetingRoomArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingRoomSpinner.setAdapter(meetingRoomArrayAdapter);

        meetingDurationInput = (TextInputLayout) findViewById(R.id.activity_add_meeting_duration_input);
        meetingAttendeesInput = (TextInputLayout) findViewById(R.id.activity_add_meeting_attendees_input);

        meetingTopicCheck = findViewById(R.id.activity_add_meeting_topic_check);
        meetingRoomCheck = findViewById(R.id.activity_add_meeting_room_check);
        meetingDurationCheck = findViewById(R.id.activity_add_meeting_duration_check);
        meetingAttendeesCheck = findViewById(R.id.activity_add_meeting_attendees_check);

        Toolbar toolbar = findViewById(R.id.activity_add_meeting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //to be put here if not the app crash when adding attendee to the list
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_add_meetings_attendee_list);

        addAttendeeButton = findViewById(R.id.activity_add_meeting_add_attendee);
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

        saveButton = (MaterialButton) findViewById(R.id.activity_add_meeting_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load data in newMeeting
                //Meeting Room
                strRoomName = selectedMeetingRoom;
                strMeetingStartDate = meetingDatePicker.getYear()+"."+meetingDatePicker.getMonth()+"."+meetingDatePicker.getDayOfMonth();//date format: yyyy.MM.dd
                strMeetingHour = meetingTimePicker.getCurrentHour()+":"+meetingTimePicker.getCurrentMinute();
                iMeetingDuration = Integer.parseInt(meetingDurationInput.getEditText().getText().toString());
                roomForTheNewMeeting = new MeetingRoom(strRoomName, strMeetingStartDate, strMeetingHour, iMeetingDuration);
                //Attendees list
                newMeetingAttendeesList = mAttendeesList;
                //Topic/name
                strNewMeetingName = meetingTopicInput.getEditText().getText().toString();

                newMeeting = new Meeting(strNewMeetingName, roomForTheNewMeeting, newMeetingAttendeesList);

                mApiService.addMeeting(newMeeting);
                finish();
            }
        });

        //Text watchers for each data
        init(meetingTopicInput, meetingTopicCheck);
        init(meetingDurationInput, meetingDurationCheck);
        init(meetingAttendeesInput, meetingAttendeesCheck);
    }

    private void init(TextInputLayout textInputLayout, ImageView imageView) {
        imageView.setEnabled(false);
        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (!(textInputLayout.getEditText().getText().toString().equals(""))){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_24));
                    imageView.setEnabled(true);
                    if (textInputLayout==meetingAttendeesInput){addAttendeeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_person_add_24_green));}
                }else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_close_24));
                    imageView.setEnabled(false);
                    if (textInputLayout==meetingAttendeesInput){addAttendeeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_person_add_24_grey));}
                }
                //method to check all data are filled before set the button enable
                updateSaveButtonStatus();
            }
        });
    }

    private void updateSaveButtonStatus(){
        bFilledCount = (meetingTopicCheck.isEnabled() && meetingRoomCheck.isEnabled()
                        && meetingDurationCheck.isEnabled() && mAttendeesList.size()>0);
        if (bFilledCount){
            saveButton.setEnabled(true);
            saveButton.setText("Ajouter la réunion");
        }else{
            saveButton.setEnabled(false);
            saveButton.setText("Renseigner tous les champs pour pouvoir ajouter la réunion");}
    }

    //for spinner room list
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            meetingRoomCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_close_24));
            meetingRoomCheck.setEnabled(false);
        }else{
            meetingRoomCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_24));
            meetingRoomCheck.setEnabled(true);
            selectedMeetingRoom = meetingRoomsList[position];
        }
    }
    //for spinner room list
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

