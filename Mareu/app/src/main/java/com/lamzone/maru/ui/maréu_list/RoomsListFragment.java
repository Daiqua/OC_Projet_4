package com.lamzone.maru.ui.maréu_list;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.app.Dialog;
import android.app.Notification;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
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
    private RecyclerView mRecyclerView;
    private RoomListFragmentRecyclerViewAdapter mRoomListFragmentRecyclerViewAdapter;

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
        mRecyclerView= view.findViewById(R.id.fragment_rooms_list_recycler_view);


        mRoomListFragmentRecyclerViewAdapter = new RoomListFragmentRecyclerViewAdapter(meetingRoomList);

        mRoomListFragmentRecyclerViewAdapter.setOnItemClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setAdapter(mRoomListFragmentRecyclerViewAdapter);

        return view;
    }



    //TODO: to comment

    public void setRoomListener(RoomFilterListener roomFilterListener){
        this.mRoomFilterListener = roomFilterListener;
    }
    //TODO: to comment
    //click listener to transfer data to frag
    @Override
    public void onItemClick(String text) {
        mRoomFilterListener.getRoomFiltered(text);
            dismiss();
    }
    //TODO: to comment
    public interface RoomFilterListener {

        void getRoomFiltered(String roomName);
    }


}
