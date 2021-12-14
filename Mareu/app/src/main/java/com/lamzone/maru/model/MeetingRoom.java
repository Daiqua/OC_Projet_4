package com.lamzone.maru.model;

public class MeetingRoom {

    private final String strMeetingRoomName;
    private final int meetingRoomColor;

    public MeetingRoom(String strMeetingRoomName, int meetingRoomColor) {

        this.strMeetingRoomName = strMeetingRoomName;
        this.meetingRoomColor = meetingRoomColor;

    }

    public String getStrMeetingRoomName() {
        return strMeetingRoomName;
    }

    public int getMeetingRoomColor() {
        return meetingRoomColor;
    }

}
