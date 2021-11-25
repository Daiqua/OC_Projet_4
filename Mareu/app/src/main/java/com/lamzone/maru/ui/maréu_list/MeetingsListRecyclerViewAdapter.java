package com.lamzone.maru.ui.maréu_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.maru.MaReuActivity;
import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.service.DummyAttendeesListGenerator;
import com.lamzone.maru.service.MaReuApiService;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MeetingsListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsListRecyclerViewAdapter.MyViewHolder> {
    private static List<Meeting> mMeetingsList;
    private MaReuApiService mApiService;
    private String attendeesEmailAddressesCommaSeparated;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView meetingName;
        private TextView meetingAttendeesList;
        private TextView meetingStartingDate;
        private TextView meetingStartingHour;
        private TextView meetingDuration;
        private ImageButton deleteMeetingButton;

        public MyViewHolder(View view) {
            super(view);
            meetingName = view.findViewById(R.id.activity_meetings_item_meeting_name);
            meetingAttendeesList = view.findViewById(R.id.activity_meetings_item_meeting_attendees_list);
            meetingStartingDate = view.findViewById(R.id.activity_meetings_item_meeting_starting_date);
            meetingStartingHour = view.findViewById(R.id.activity_meetings_item_meeting_starting_hour);
            meetingDuration = view.findViewById(R.id.activity_meetings_item_meeting_duration);
            deleteMeetingButton = view.findViewById(R.id.activity_meetings_item_delete_button);
        }

    }

    public MeetingsListRecyclerViewAdapter (List<Meeting> meetingsList) {this.mMeetingsList = meetingsList;}


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_meeting_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mApiService = DI.getApiService();
        Meeting meeting = mMeetingsList.get(position);
        holder.meetingName.setText(meeting.getStrMeetingName());
        attendeesEmailAddressesCommaSeparated = mApiService
                .getAttendeesListEmailAddresses(meeting.getMeetingAttendeesList());
        holder.meetingAttendeesList.setText(attendeesEmailAddressesCommaSeparated);
        holder.meetingStartingDate.setText("le "+writeDate(generateDate(meeting)));
        holder.meetingStartingHour.setText("à "+writeHour(generateDate(meeting)));
        holder.meetingDuration.setText("Durée: "+meeting.getMeetingRoom().getMeetingDuration()+" min");

        holder.deleteMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiService.deleteMeeting(meeting);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {return mMeetingsList.size();}

    //TODO: move generateDate, writeHour and writeDate in ApiService or DummyMeetingRoomsGenerator
    public static Date generateDate (Meeting meeting) {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.FRANCE);
        try {
            date = dateFormatter.parse(meeting.getMeetingRoom().getStrMeetingStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String writeHour (Date date){

        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
        String meetingStartinghour = dateFormatter.format(date);
        return meetingStartinghour;
    }

    public static String writeDate (Date date){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM", Locale.FRANCE);
        String meetingStartingDate = dateFormatter.format(date);
        return meetingStartingDate;
    }
}
