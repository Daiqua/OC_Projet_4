package com.lamzone.maru.service;

import com.lamzone.maru.model.MeetingRoom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingRoomGenerator {

    //TODO: create unmodifiable list of room
    public static List<MeetingRoom> DUMMY_MEETING_ROOMS = Arrays.asList(

            new MeetingRoom("salle 1","2021.11.22 11:30:00", 45),
            new MeetingRoom("salle 2","2021.11.22 11:31:00", 30),
            new MeetingRoom("salle 3","2021.11.22 11:32:00", 50),
            new MeetingRoom("salle 4","2021.11.22 11:33:00", 45),
            new MeetingRoom("salle 5","2021.11.22 11:34:00", 45),
            new MeetingRoom("salle 6","2021.11.22 11:35:00", 45),
            new MeetingRoom("salle 7","2021.11.22 11:36:00", 30),
            new MeetingRoom("salle 8","2021.11.22 11:37:00", 20),
            new MeetingRoom("salle 9","2021.11.22 11:38:00", 15),
            new MeetingRoom("salle 10","2021.11.22 11:39:00", 20)
    );

    static List<MeetingRoom>generateMeetingRooms() {
        return new ArrayList<>(DUMMY_MEETING_ROOMS);}

}
