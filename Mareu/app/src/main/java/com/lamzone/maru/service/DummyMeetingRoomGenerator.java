package com.lamzone.maru.service;

import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingRoomGenerator {

    public static List<MeetingRoom> DUMMY_MEETING_ROOMS = Arrays.asList(
            new MeetingRoom("salle 1"),
            new MeetingRoom("salle 2"),
            new MeetingRoom("salle 3"),
            new MeetingRoom("salle 4"),
            new MeetingRoom("salle 5"),
            new MeetingRoom("salle 6"),
            new MeetingRoom("salle 7"),
            new MeetingRoom("salle 8"),
            new MeetingRoom("salle 9"),
            new MeetingRoom("salle 10")
    );

    static List<MeetingRoom>generateMeetingRooms() {return new ArrayList<>(DUMMY_MEETING_ROOMS);}
}
