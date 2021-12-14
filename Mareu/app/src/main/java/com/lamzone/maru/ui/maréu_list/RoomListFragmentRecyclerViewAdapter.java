package com.lamzone.maru.ui.maréu_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.MeetingRoom;
import com.lamzone.maru.service.MaReuApiService;

import java.util.List;

public class RoomListFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RoomListFragmentRecyclerViewAdapter.MyViewHolder>{
    private final List<MeetingRoom> sMeetingRoomsList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final Button mButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.fragment_room_list_item_button);
        }
    }

    public RoomListFragmentRecyclerViewAdapter (List<MeetingRoom> meetingRoomsList){
        sMeetingRoomsList = meetingRoomsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rooms_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mButton.setText(sMeetingRoomsList.get(position).getStrMeetingRoomName());
        holder.mButton.setBackgroundColor(sMeetingRoomsList.get(position).getMeetingRoomColor());
        /*holder.itemView.setOnClickListener(v -> {

        }*/

    }

    @Override
    public int getItemCount() {
        return sMeetingRoomsList.size();
    }

}
