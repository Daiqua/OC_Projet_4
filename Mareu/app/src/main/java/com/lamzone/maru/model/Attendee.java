package com.lamzone.maru.model;

public class Attendee {

    private String strAttendeeName;
    private String strAttendeeEmailAddress;

    public Attendee(String strAttendeeName, String strAttendeeEmailAddress) {
        this.strAttendeeName = strAttendeeName;
        this.strAttendeeEmailAddress = strAttendeeEmailAddress;

    }

    public Attendee(String strAttendeeEmailAddress) {
        this("", strAttendeeEmailAddress);

    }

    public String getStrAttendeeName() {
        return strAttendeeName;
    }

    public void setStrAttendeeName(String strAttendeeName) {
        this.strAttendeeName = strAttendeeName;
    }

    public String getStrAttendeeEmailAddress() {
        return strAttendeeEmailAddress;
    }

    public void setStrAttendeeEmailAddress(String strAttendeeEmailAddress) {
        this.strAttendeeEmailAddress = strAttendeeEmailAddress;
    }
}
