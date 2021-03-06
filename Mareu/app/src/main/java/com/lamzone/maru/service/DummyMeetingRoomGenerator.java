package com.lamzone.maru.service;

import android.graphics.Color;

import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingRoomGenerator {

    private static final String[] roomsList = {"", "salle 1", "salle 2", "salle 3", "salle 4", "salle 5",
            "salle 6", "salle 7", "salle 8", "salle 9", "salle 10"};

    private static final String[] roomsColors = {"#A79C9B", "#e67388", "#ff815e", "#ffc75e", "#ffeb5f", "#bdd54e", "#30802f",
            "#3f5aab", "#5f4fd6", "#603082", "#9368a6"};

    public static List<MeetingRoom> DUMMY_MEETING_ROOMS = Arrays.asList(
            new MeetingRoom(roomsList[0], Color.parseColor(roomsColors[0])),
            new MeetingRoom(roomsList[1], Color.parseColor(roomsColors[1])),
            new MeetingRoom(roomsList[2], Color.parseColor(roomsColors[2])),
            new MeetingRoom(roomsList[3], Color.parseColor(roomsColors[3])),
            new MeetingRoom(roomsList[4], Color.parseColor(roomsColors[4])),
            new MeetingRoom(roomsList[5], Color.parseColor(roomsColors[5])),
            new MeetingRoom(roomsList[6], Color.parseColor(roomsColors[6])),
            new MeetingRoom(roomsList[7], Color.parseColor(roomsColors[7])),
            new MeetingRoom(roomsList[8], Color.parseColor(roomsColors[8])),
            new MeetingRoom(roomsList[9], Color.parseColor(roomsColors[9])),
            new MeetingRoom(roomsList[10], Color.parseColor(roomsColors[10]))

    );


    static List<MeetingRoom> generateMeetingRoomsList() {
        return new ArrayList<>(DUMMY_MEETING_ROOMS);
    }

    public static String[] getRoomsList() {
        return roomsList;
    }

}
