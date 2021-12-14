package com.lamzone.maru.ui.maréu_list;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.service.DateConvertor;
import com.lamzone.maru.service.DummyMeetingRoomGenerator;
import com.lamzone.maru.service.MaReuApiService;

import java.util.List;

public class MeetingsListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsListRecyclerViewAdapter.MyViewHolder> {
    private static List<Meeting> mMeetingsList;
    private MaReuApiService mApiService;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView meetingName;
        private final TextView meetingAttendeesList;
        private final TextView meetingStartingDate;
        private final TextView meetingStartingHour;
        private final TextView meetingDuration;
        private final ImageButton deleteMeetingButton;

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

    public MeetingsListRecyclerViewAdapter(List<Meeting> meetingsList) {
        mMeetingsList = meetingsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_meeting_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mApiService = DI.getApiService();
        Meeting meeting = mMeetingsList.get(position);
        String attendeesEmailAddressesCommaSeparated = mApiService
                .getAttendeesListEmailAddresses(meeting.getMeetingAttendeesList());

        //set texts
        holder.meetingName.setText(meeting.getStrMeetingName());
        holder.meetingAttendeesList.setText(attendeesEmailAddressesCommaSeparated);
        holder.meetingStartingDate.setText("le "
                + DateConvertor.convert_yyyy_MM_dd_to_dd_MMMM(meeting.getStrMeetingStartDate()));
        holder.meetingStartingHour.setText("à " + meeting.getStrMeetingStartHour());
        holder.meetingDuration.setText("Durée: " + meeting.getMeetingDuration() + " min");

        //set colors
        holder.itemView.setBackgroundColor(meeting.getMeetingRoom().getMeetingRoomColor());

        holder.deleteMeetingButton.setOnClickListener(v -> {
            mApiService.deleteMeeting(meeting);
            notifyDataSetChanged();
        });

        //TODO remove depending on the presentation meeting
        holder.itemView.setOnClickListener(v -> Toast.makeText
                (v.getContext(), "La réunion se déroulera en "
                + meeting.getMeetingRoom().getStrMeetingRoomName(), Toast.LENGTH_SHORT).show());

    }

    @Override
    public int getItemCount() {
        return mMeetingsList.size();
    }
}
