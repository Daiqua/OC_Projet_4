package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<List<Attendee>> listsOfAttendees = DummyAttendeesListGenerator.getDummyAttendeesLists();
    public static List<MeetingRoom> mMeetingRooms = DummyMeetingRoomGenerator.generateMeetingRooms();


    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(

        new Meeting("Réunion 1", mMeetingRooms.get(0), listsOfAttendees.get(0)),
        new Meeting("Réunion 2", mMeetingRooms.get(1), listsOfAttendees.get(1)),
        new Meeting("Réunion 3", mMeetingRooms.get(2), listsOfAttendees.get(2)),
        new Meeting("Réunion 4", mMeetingRooms.get(3), listsOfAttendees.get(3)),
        new Meeting("Réunion 5", mMeetingRooms.get(4), listsOfAttendees.get(4)),
        new Meeting("Réunion 6", mMeetingRooms.get(5), listsOfAttendees.get(5)),
        new Meeting("Réunion 7", mMeetingRooms.get(6), listsOfAttendees.get(6)),
        new Meeting("Réunion 8", mMeetingRooms.get(7), listsOfAttendees.get(7))
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
