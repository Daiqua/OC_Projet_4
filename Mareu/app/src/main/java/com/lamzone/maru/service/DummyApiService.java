package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.List;

public class DummyApiService implements MaReuApiService {

    private List<Attendee> attendeesList = DummyAttendeeGenerator.generateAttendees();
    private final List<List<Attendee>> listsOfAttendees =
            DummyAttendeesListGenerator.getDummyAttendeesLists();
    private final List<MeetingRoom> meetingRoomList = DummyMeetingRoomGenerator.generateMeetingRoomsList();
    private final List<Meeting> meetingsList = DummyMeetingGenerator.generateMeetings();
    private final List<Meeting> filteredMeetingsList = new ArrayList<>();


    @Override
    public List<Meeting> getMeetings() {
        return meetingsList;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingsList.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingsList.remove(meeting);
    }

    @Override
    public String[] getRoomsList() {
        return DummyMeetingRoomGenerator.getRoomsList();
    }

    @Override
    public List<Meeting> generateDateFilteredList(String strDatePattern_yyyy_MM_dd) {
        filteredMeetingsList.clear();
        for (Meeting meeting : meetingsList) {
            String strTempMeetingDate = meeting.getStrMeetingStartDate();//format: yyyy.MM.dd
            if (strTempMeetingDate.equals(strDatePattern_yyyy_MM_dd)) {
                filteredMeetingsList.add(meeting);
            }
        }
        return filteredMeetingsList;
    }

    @Override
    public List<Meeting> generateRoomFilteredList(String strMeetingRoomName) {
        filteredMeetingsList.clear();
        for (Meeting meeting : meetingsList) {
            String strTempMeetingRoom = meeting.getMeetingRoom().getStrMeetingRoomName();
            if (strTempMeetingRoom.equals(strMeetingRoomName)) {
                filteredMeetingsList.add(meeting);
            }
        }
        return filteredMeetingsList;
    }

    @Override
    public String getAttendeesListEmailAddresses(List<Attendee> attendeesList) {
        this.attendeesList = attendeesList;
        return DummyAttendeesListGenerator.getAttendeesListEmailAddresses(attendeesList);
    }

    @Override
    public List<Attendee> getAttendees() {
        return attendeesList;
    }

    @Override
    public void addAttendees(Attendee attendee) {
        attendeesList.add(attendee);
    }

    @Override
    public List<List<Attendee>> getListsOfAttendees() {
        return listsOfAttendees;
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return meetingRoomList;
    }

}
