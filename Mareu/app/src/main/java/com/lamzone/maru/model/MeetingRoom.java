package com.lamzone.maru.model;

import java.util.List;

public class MeetingRoom {

    private String strMeetingRoomName;
    //TODO: add availability

    public MeetingRoom (String strMeetingRoomName){
        this.strMeetingRoomName = strMeetingRoomName;
    }

    public String getStrMeetingRoomName() {
        return strMeetingRoomName;
    }

    public void setStrMeetingRoomName(String strMeetingRoomName) {
        this.strMeetingRoomName = strMeetingRoomName;
    }
}
