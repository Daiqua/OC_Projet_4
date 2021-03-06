package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;

import java.util.List;

public interface MaReuApiService {

    List<Meeting> getMeetings();

    void addMeeting(Meeting meeting);

    List<Attendee> getAttendees();

    void addAttendees(Attendee attendee);

    List<List<Attendee>> getListsOfAttendees();

    List<MeetingRoom> getMeetingRooms();

    String getAttendeesListEmailAddresses(List<Attendee> attendeesList);

    void deleteMeeting(Meeting meeting);

    String[] getRoomsList();

    List<Meeting> generateDateFilteredList(String strDatePattern_yyyy_MM_dd);

    List<Meeting> generateRoomFilteredList(String strMeetingRoomName);
}
