package com.lamzone.maru.service;

import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {


    private static List<Meeting> DUMMY_MEETINGS = new ArrayList<>();
    private static MaReuApiService mApiService;
    private static  List<List<Attendee>> listsOfAttendees = new ArrayList<>();
    private static List<MeetingRoom> mMeetingRooms = new ArrayList<>();


        static List<Meeting> generateMeetings() {
            mApiService  = DI.getApiService();
            listsOfAttendees = mApiService.getListsOfAttendees();
            mMeetingRooms = mApiService.getMeetingRooms();

            DUMMY_MEETINGS.clear();

            Meeting meeting = new Meeting("Réunion 1",mMeetingRooms.get(0), listsOfAttendees.get(0) );
            DUMMY_MEETINGS.add(meeting);

            meeting= new Meeting("Réunion 2",mMeetingRooms.get(1), listsOfAttendees.get(1) );
            DUMMY_MEETINGS.add(meeting);

            meeting = new Meeting("Réunion 3",mMeetingRooms.get(2), listsOfAttendees.get(2) );
            DUMMY_MEETINGS.add(meeting);

            meeting = new Meeting("Réunion 4",mMeetingRooms.get(3), listsOfAttendees.get(3) );
            DUMMY_MEETINGS.add(meeting);

            meeting = new Meeting("Réunion 5",mMeetingRooms.get(4), listsOfAttendees.get(4) );
            DUMMY_MEETINGS.add(meeting);

            return new ArrayList<>(DUMMY_MEETINGS);
    }


}
