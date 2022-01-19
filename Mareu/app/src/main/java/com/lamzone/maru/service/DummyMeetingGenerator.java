package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<List<Attendee>> listsOfAttendees = DummyAttendeesListGenerator.getDummyAttendeesLists();
    public static List<MeetingRoom> mMeetingRooms = DummyMeetingRoomGenerator.generateMeetingRoomsList();


    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(

            new Meeting("Réunion 1", mMeetingRooms.get(1), listsOfAttendees.get(0),
                    "2021.11.22", "11:00", 45),
            new Meeting("Réunion 2", mMeetingRooms.get(2), listsOfAttendees.get(1),
                    "2021.11.22", "11:01", 20),
            new Meeting("Réunion 3", mMeetingRooms.get(3), listsOfAttendees.get(2),
                    "2021.11.22", "11:02", 30),
            new Meeting("Réunion 4", mMeetingRooms.get(4), listsOfAttendees.get(3),
                    "2021.11.22", "11:03", 18),
            new Meeting("Réunion 5", mMeetingRooms.get(5), listsOfAttendees.get(4),
                    "2021.11.22", "11:04", 50),
            new Meeting("Réunion 6", mMeetingRooms.get(6), listsOfAttendees.get(5),
                    "2021.11.22", "11:05", 60),
            new Meeting("Réunion 7", mMeetingRooms.get(7), listsOfAttendees.get(6),
                    "2021.11.22", "11:06", 23),
            new Meeting("Réunion 8", mMeetingRooms.get(8), listsOfAttendees.get(7),
                    "2021.11.23", "11:07", 46),
            new Meeting("Réunion 9", mMeetingRooms.get(9), listsOfAttendees.get(8),
                    "2021.12.13", "11:08", 45),
            new Meeting("Réunion 10", mMeetingRooms.get(10), listsOfAttendees.get(9),
                    "2021.12.13", "11:09", 35)

    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
