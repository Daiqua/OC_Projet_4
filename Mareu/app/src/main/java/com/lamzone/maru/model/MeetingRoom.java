package com.lamzone.maru.model;

import java.text.DateFormat;
import java.util.Date; //API lvl1 //TODO: check with Brahim
import java.util.List;

public class MeetingRoom {

    private String strMeetingRoomName;
    private String strMeetingStartDate; //pattern "yyyy.MM.dd G 'at' HH:mm:ss z" will be transform as date after using SimpleDateFormat
    private String strMeetingStartHour; //pattern "yyyy.MM.dd G 'at' HH:mm:ss z" will be transform as date after using SimpleDateFormat
    private int meetingDuration;

    public MeetingRoom(String strMeetingRoomName, String strMeetingStartDate,
                       String strMeetingStartHour, int meetingDuration) {

        this.strMeetingRoomName = strMeetingRoomName;
        this.strMeetingStartDate = strMeetingStartDate;
        this.strMeetingStartHour = strMeetingStartHour;
        this.meetingDuration = meetingDuration;
    }

    public MeetingRoom() {

        this.strMeetingRoomName = "salle non définie";
        this.strMeetingStartDate = "date non définie";
        this.strMeetingStartHour = "";
        this.meetingDuration = 0;
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

    public String getStrMeetingStartHour() {
        return strMeetingStartHour;
    }

    public void setStrMeetingStartHour(String strMeetingStartHour) {
        this.strMeetingStartHour = strMeetingStartHour;
    }
}
