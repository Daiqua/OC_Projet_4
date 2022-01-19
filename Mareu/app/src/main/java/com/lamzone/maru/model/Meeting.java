package com.lamzone.maru.model;

import java.util.List;

public class Meeting {

    private final String strMeetingName;
    private final MeetingRoom meetingRoom;
    private final List<Attendee> meetingAttendeesList;
    private final String strMeetingStartDate; //pattern "yyyy.MM.dd"
    private final String strMeetingStartHour;
    private final int meetingDuration;

    public Meeting (String strMeetingName, MeetingRoom meetingRoom,
                   List<Attendee> meetingAttendeesList, String strMeetingStartDate,
                    String strMeetingStartHour, int meetingDuration) {
        this.strMeetingName = strMeetingName;
        this.meetingRoom = meetingRoom;
        this.meetingAttendeesList = meetingAttendeesList;
        this.strMeetingStartDate = strMeetingStartDate;
        this.strMeetingStartHour = strMeetingStartHour;
        this.meetingDuration = meetingDuration;
    }

    public String getStrMeetingName() {
        return strMeetingName;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public List<Attendee> getMeetingAttendeesList() {
        return meetingAttendeesList;
    }


    public String getStrMeetingStartDate() {
        return strMeetingStartDate;
    }

    public String getStrMeetingStartHour() {
        return strMeetingStartHour;
    }

    public int getMeetingDuration() {
        return meetingDuration;
    }

}
