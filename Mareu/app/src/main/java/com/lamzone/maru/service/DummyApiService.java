package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.List;

public class DummyApiService implements MaReuApiService{

    List<Attendee> attendeesList;
    List<List<Attendee>> listsOfAttendees;
    List<MeetingRoom> meetingRoomList;
    List<Meeting> meetingsList;


    @Override
    public List<Meeting> getMeetings() {

        meetingsList = DummyMeetingGenerator.generateMeetings();
        return meetingsList;
    }

    @Override
    public void addMeeting(Meeting meeting) {meetingsList.add(meeting);
    }

    @Override
    public List<Attendee> getAttendees() {
        attendeesList = DummyAttendeeGenerator.generateAttendees();
        return attendeesList;
    }

    @Override
    public void addAttendees(Attendee attendee) {attendeesList.add(attendee);}

    @Override
    public List<List<Attendee>> getListsOfAttendees() {
        listsOfAttendees = DummyAttendeesListGenerator.generateAttendeesLists();
        return listsOfAttendees;
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        meetingRoomList = DummyMeetingRoomGenerator.generateMeetingRooms();
        return meetingRoomList;
    }

    @Override
    public void removeMeeting(Meeting meeting) {meetingsList.remove(meeting);}

    @Override
    public void getMeetingsAtDate() {

    }

    @Override
    public void getMeetingInMeetingRoom(MeetingRoom meetingRoom) {

    }

    @Override
    public String getAttendeesListEmailAddresses(List<Attendee> attendeesList) {
        this.attendeesList = attendeesList;
        return DummyAttendeesListGenerator.getAttendeesListEmailAddresses(attendeesList);
    }
}
