package com.lamzone.maru.model;

import java.text.DateFormat;
import java.util.Date; //API lvl1 //TODO: check with Brahim
import java.util.List;

public class MeetingRoom {

    private String strMeetingRoomName;
    private String strMeetingStartDate; //pattern "yyyy.MM.dd G 'at' HH:mm:ss z" will be transform as date after using SimpleDateFormat
    private int meetingDuration;
    //TODO: add availability

    public MeetingRoom (String strMeetingRoomName, String strMeetingStartDate, int meetingDuration){

        this.strMeetingRoomName = strMeetingRoomName;
        this.strMeetingStartDate = strMeetingStartDate;
        this.meetingDuration = meetingDuration;
    }

    public String getStrMeetingRoomName() {
        return strMeetingRoomName;
    }

    public void setStrMeetingRoomName(String strMeetingRoomName) {
        this.strMeetingRoomName = strMeetingRoomName;
    }

    public String getStrMeetingStartDate() {
        return strMeetingStartDate;
    }

    public void setStrMeetingStartDate(String strMeetingStartDate) {
        this.strMeetingStartDate = strMeetingStartDate;
    }

    public int getMeetingDuration() {
        return meetingDuration;
    }

    public void setMeetingDuration(int meetingDuration) {
        this.meetingDuration = meetingDuration;
    }
}
