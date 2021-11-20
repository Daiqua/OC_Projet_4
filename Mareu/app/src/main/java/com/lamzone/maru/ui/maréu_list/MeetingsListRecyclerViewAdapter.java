package com.lamzone.maru.ui.maréu_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.service.DummyAttendeesListGenerator;
import com.lamzone.maru.service.MaReuApiService;

import java.util.ArrayList;
import java.util.List;

public class MeetingsListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsListRecyclerViewAdapter.MyViewHolder> {
    private final List<Meeting> mMeetingsList;
    public static int iAdapterPosition;
    private MaReuApiService mApiService;
    private String attendeesEmailAddressesCommaSeparated;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView meetingName;
        private TextView meetingAttendeesList;
        private TextView meetingDate;
        private TextView meetingHour;




        public MyViewHolder(View view) {
            super(view);
            meetingName = view.findViewById(R.id.activity_meetings_item_meeting_name);
            meetingAttendeesList = view.findViewById(R.id.activity_meetings_item_meeting_attendees_list);
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
    }

    @Override
    public int getItemCount() {return mMeetingsList.size();}

}
