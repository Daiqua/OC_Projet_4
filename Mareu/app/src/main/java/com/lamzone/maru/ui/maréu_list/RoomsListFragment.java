package com.lamzone.maru.ui.maréu_list;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.MeetingRoom;
import com.lamzone.maru.service.MaReuApiService;

import java.util.List;

public class RoomsListFragment extends DialogFragment implements RoomListFragmentRecyclerViewAdapter.OnItemClickListener {

    private final MaReuApiService mApiService = DI.getApiService();
    private RoomFilterListener mRoomFilterListener = null;

    public RoomsListFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<MeetingRoom> meetingRoomList = mApiService.getMeetingRooms();
        View view = inflater.inflate(R.layout.fragment_rooms_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_rooms_list_recycler_view);

        RoomListFragmentRecyclerViewAdapter roomListFragmentRecyclerViewAdapter =
                new RoomListFragmentRecyclerViewAdapter(meetingRoomList);

        roomListFragmentRecyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(roomListFragmentRecyclerViewAdapter);

        return view;
    }

    public void setRoomListener(RoomFilterListener roomFilterListener) {
        this.mRoomFilterListener = roomFilterListener;
    }

    //click listener to listen RoomListRecyclerView
    @Override
    public void onItemClick(String text) {
        mRoomFilterListener.getRoomFiltered(text);
        dismiss();
    }

    public interface RoomFilterListener {

        void getRoomFiltered(String roomName);
    }


}
