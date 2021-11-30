package com.lamzone.maru.ui.maréu_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.maru.R;
import com.lamzone.maru.model.Attendee;

import java.util.List;

public class AddMeetingAttendeesListRecyclerViewAdapter extends RecyclerView.Adapter<AddMeetingAttendeesListRecyclerViewAdapter.MyViewHolder> {
    private List<Attendee> mAttendeesList;
    private Attendee mAttendee;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView attendeeEmailAddress;
        private ImageButton attendeeListDeleteButton;


        public MyViewHolder(View view) {
            super(view);
            attendeeEmailAddress = view.findViewById(R.id.activity_add_meetings_attendee_list_attendee_email);
            attendeeListDeleteButton = view.findViewById(R.id.activity_add_meetings_attendee_list_delete_button);
        }
    }

    public AddMeetingAttendeesListRecyclerViewAdapter(List<Attendee> attendeesList) {
        this.mAttendeesList = attendeesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_meeting_attendee_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mAttendee = new Attendee(mAttendeesList.get(position).getStrAttendeeEmailAddress());
        holder.attendeeEmailAddress.setText(mAttendee.getStrAttendeeEmailAddress());

        holder.attendeeListDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAttendeesList.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAttendeesList.size();
    }
}
