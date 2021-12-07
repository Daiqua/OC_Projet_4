package com.lamzone.maru.model;

import java.util.List;

public class Meeting {

    private String strMeetingName;
    private MeetingRoom meetingRoom;
    private List<Attendee> meetingAttendeesList;

    public Meeting (String strMeetingName, MeetingRoom meetingRoom,
                   List<Attendee> meetingAttendeesList) {
        this.strMeetingName = strMeetingName;
        this.meetingRoom = meetingRoom;
        this.meetingAttendeesList = meetingAttendeesList;
    }

    public Meeting () {
        this("non renseigné", new MeetingRoom(),null);
    }

    public String getStrMeetingName() {
        return strMeetingName;
    }

    public void setStrMeetingName(String strMeetingName) {
        this.strMeetingName = strMeetingName;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingPlace(MeetingRoom meetingPlace) {
        this.meetingRoom = meetingRoom;
    }

    public List<Attendee> getMeetingAttendeesList() {
        return meetingAttendeesList;
    }

    public void setMeetingAttendeesList(List<Attendee> meetingAttendeesList) {
        this.meetingAttendeesList = meetingAttendeesList;
    }
}
