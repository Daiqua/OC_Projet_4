package com.lamzone.maru.service;

import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingRoomGenerator {

    private static final String[] roomsList = {"","salle 1","salle 2","salle 3","salle 4","salle 5",
                                        "salle 6","salle 7","salle 8","salle 9","salle 10"};

    public static List<MeetingRoom> DUMMY_MEETING_ROOMS = Arrays.asList(

            new MeetingRoom(getRoomsList()[1], "2021.11.22" ,"11:30", 45),
            new MeetingRoom(getRoomsList()[2],"2021.11.22","11:31", 30),
            new MeetingRoom(getRoomsList()[3],"2021.11.22","11:32", 50),
            new MeetingRoom(getRoomsList()[4],"2021.11.22","11:33", 45),
            new MeetingRoom(getRoomsList()[5],"2021.11.22","11:34", 45),
            new MeetingRoom(getRoomsList()[6],"2021.11.22","11:35", 45),
            new MeetingRoom(getRoomsList()[7],"2021.11.22","11:36", 30),
            new MeetingRoom(getRoomsList()[8],"2021.11.22","11:37", 20),
            new MeetingRoom(getRoomsList()[9],"2021.11.22","11:38", 15),
            new MeetingRoom(getRoomsList()[10],"2021.11.22","11:39", 20)
    );

    static List<MeetingRoom>generateMeetingRooms() {
        return new ArrayList<>(DUMMY_MEETING_ROOMS);}

    static String[] getRoomsList() {
        return roomsList;
    }

}
