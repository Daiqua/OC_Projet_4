package com.lamzone.maru.service;

import android.graphics.Color;

import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.List;

public abstract class DummyMeetingRoomGenerator {

    private static final String[] roomsList = {"", "salle 1", "salle 2", "salle 3", "salle 4", "salle 5",
            "salle 6", "salle 7", "salle 8", "salle 9", "salle 10"};

    private static final String[] roomsColors = {"#A79C9B", "#e67388", "#ff815e", "#ffc75e","#ffeb5f", "#bdd54e", "#30802f",
            "#3f5aab", "#5f4fd6", "#603082", "#9368a6"};

    public static List<MeetingRoom> DUMMY_MEETING_ROOMS;


    static List<MeetingRoom> generateMeetingRooms() {

        for (int i=0; i<10; i++) {
            DUMMY_MEETING_ROOMS.add(new MeetingRoom(roomsList[i], Color.parseColor(roomsColors[i])));
        }

        return new ArrayList<>(DUMMY_MEETING_ROOMS);
    }

    public static String[] getRoomsList() {
        return roomsList;
    }

    public static int getRoomColor(String roomName){
        return DUMMY_MEETING_ROOMS.get(DUMMY_MEETING_ROOMS.indexOf(roomName)).getMeetingRoomColor();
    }





}
