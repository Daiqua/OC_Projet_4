package com.lamzone.maru.ui.maréu_list;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.lamzone.maru.MaReuActivity;
import com.lamzone.maru.R;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.service.DummyApiService;
import com.lamzone.maru.service.MaReuApiService;

import java.util.List;

public class RoomsListFragment extends DialogFragment {

    Button buttonRoom1;
    Button buttonRoom2;
    Button buttonRoom3;
    Button buttonRoom4;
    Button buttonRoom5;
    Button buttonRoom6;
    Button buttonRoom7;
    Button buttonRoom8;
    Button buttonRoom9;
    Button buttonRoom10;

    private MaReuApiService mApiService = DI.getApiService();;
    private String[] roomList={};


    public RoomsListFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);

    }

    public static RoomsListFragment newInstance() {
        RoomsListFragment fragment = new RoomsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        roomList = mApiService.getRoomsList(); // room index 0 is empty.
        View view = inflater.inflate(R.layout.fragment_rooms_list, container, false);
        buttonRoom1 = view.findViewById(R.id.fragment_room_list_room1);
        setButtonRoom(buttonRoom1, roomList[1]);
        buttonRoom2 = view.findViewById(R.id.fragment_room_list_room2);
        setButtonRoom(buttonRoom2, roomList[2]);
        buttonRoom3 = view.findViewById(R.id.fragment_room_list_room3);
        setButtonRoom(buttonRoom3, roomList[3]);
        buttonRoom4 = view.findViewById(R.id.fragment_room_list_room4);
        setButtonRoom(buttonRoom4, roomList[4]);
        buttonRoom5 = view.findViewById(R.id.fragment_room_list_room5);
        setButtonRoom(buttonRoom5, roomList[5]);
        buttonRoom6 = view.findViewById(R.id.fragment_room_list_room6);
        setButtonRoom(buttonRoom6, roomList[6]);
        buttonRoom7 = view.findViewById(R.id.fragment_room_list_room7);
        setButtonRoom(buttonRoom7, roomList[7]);
        buttonRoom8 = view.findViewById(R.id.fragment_room_list_room8);
        setButtonRoom(buttonRoom8, roomList[8]);
        buttonRoom9 = view.findViewById(R.id.fragment_room_list_room9);
        setButtonRoom(buttonRoom9, roomList[9]);
        buttonRoom10 = view.findViewById(R.id.fragment_room_list_room10);
        setButtonRoom(buttonRoom10, roomList[10]);

        return view;
    }

    private void setButtonRoom(Button button, String roomName){
        button.setText(roomName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaReuActivity.setISRoomFilterActivated(true);
                MaReuActivity.setStrRoomFiltered(roomName);
                Intent intent = new Intent(getActivity(), MaReuActivity.class);
                startActivity(intent);
                dismiss();
            }
        });
    }
}
